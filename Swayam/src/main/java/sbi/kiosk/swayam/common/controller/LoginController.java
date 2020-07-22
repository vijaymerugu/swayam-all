package sbi.kiosk.swayam.common.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import sbi.kiosk.swayam.common.constants.ExceptionConstants;
import sbi.kiosk.swayam.common.dto.MenuMasterDto;
import sbi.kiosk.swayam.common.dto.RequestResponseLogDto;
import sbi.kiosk.swayam.common.dto.ResponseDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.repository.UserRepository;
import sbi.kiosk.swayam.common.service.LoginService;
import sbi.kiosk.swayam.common.utils.CommonUtils;

@RestController
public class LoginController{ 
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	LoginService loginService;
	
	@Autowired
	UserRepository userRepo;
	
	@Value(value = "${oms_url}")
	private String oms_url;
	
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@PostAuthorize("hasPermission('login','READ')")
	public ModelAndView login(@RequestParam("pfId") String pfId, HttpSession session) {	
		
		UserDto userObj = loginService.getRoleByUsername(pfId);
		
		session.setAttribute("pfId", userObj.getPfId());
		session.setAttribute("userObj", userObj);
		
		
		logger.info("Session Val"+ session.getAttribute("pfId"));
		ModelAndView mav = new ModelAndView("home");
		return mav;		
	}
	
	@RequestMapping(value="logout")
	@PreAuthorize("hasPermission('logout','READ')")
	public ModelAndView logout(ModelAndView model,HttpSession session) {	
		
		session.invalidate();
		
		model.setViewName("redirect:https://adfs.sbi.co.in/adfs/ls/?wa=wsignout1.0");
		return model;
	}
	
	@RequestMapping(value="common/menu", method=RequestMethod.GET)
	@PreAuthorize("hasPermission('commonMenu','READ')")
	public List<MenuMasterDto> getMenu(HttpSession session) {		
		UserDto userObj =(UserDto) session.getAttribute("userObj");
		//session.setAttribute("username", username);
		logger.info("Session Val1111"+ session.getAttribute("pfId"));
		
		
		return loginService.getMenusByUserRole(userObj.getRole());
		//ModelAndView mav = new ModelAndView("home");
		//return mav;		
	}
	
	@RequestMapping("summary")
	public ModelAndView summary() {
		ModelAndView mav = new ModelAndView("userlist");
		return mav;
	}
	

	@SuppressWarnings("finally")
	@RequestMapping(value = "${sbi_url}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAuthentication(@RequestParam("token") String token,HttpServletResponse res) throws Exception {
		String output="";
		ResponseDto response =null;
		
		  try {
			  
			  logger.info("oms_url post 1 request:::: "+oms_url);
			  
		        System.err.println("inside createAuthentication token "+token);
		    if((token!=null && !token.isEmpty()) && (!token.equals("") && !token.equals("null"))){
		    	
			    URL url = new URL(oms_url);
			    logger.info("oms_url post request:::: "+oms_url);
		        Map<String,Object> params = new LinkedHashMap<>();
		        params.put("token", token);
		        StringBuilder postData = new StringBuilder();
			        
	        for (Map.Entry<String,Object> param : params.entrySet()) {
	            if (postData.length() != 0) postData.append('&');
	            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	            postData.append('=');
	            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
	        }
		        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	
		        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		        conn.setRequestMethod("POST");
		        conn.setRequestProperty("Content-Type", "application/json");
		        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
		        conn.setDoOutput(true);
		        conn.getOutputStream().write(postDataBytes);
	
		        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

			       
			if ((output = br.readLine()) != null) {
			   Gson gson = new Gson();
			   logger.info("response1==="+output);
		       Map map =  gson.fromJson(output, Map.class);
			   logger.info("response2==="+map);
			   
			   String userId=(String) map.get("UserId");
			   String result=(String) map.get("Result");
			   logger.info("response3==="+result);
			   
			   response =new ResponseDto();
			   RequestResponseLogDto reqRespLogDto=new RequestResponseLogDto();
			   
			   reqRespLogDto.setUserId(userId);
			   reqRespLogDto.setToken(token); 
			   reqRespLogDto.setRequest(token);
			   reqRespLogDto.setUrl(oms_url);
			   reqRespLogDto.setSuccess(result);
			   reqRespLogDto.setResponse(output);
			   
			   
			   loginService.saveReqAndResponse(reqRespLogDto);
			  
					
			if(userId!=null && !userId.isEmpty()  && result!=null && result.equalsIgnoreCase("Success")){
			
				
				// check userId in db
				String dbPfId=userRepo.findIdByPfId(userId);
				logger.info("dbPfId=="+dbPfId);
				response.setPfId(dbPfId);
				response.setResponse(result);
				if(userId.equals(dbPfId)){
					// call login method
					
					//ModelAndView mav = new ModelAndView("login");
					//mav.setViewName("redirect:/login");
					
					//res.sendRedirect("/login");
					
					
				}else{
					response.setResponse("UserId is not Exist");
				}
			}else{
				response.setResponse("Invalid Respons");
			}
			 }   
			}
		 
					
		} catch (Exception e) {
			logger.error("Exception "+ExceptionConstants.AUTHENTICATION_ERROR);
			
		}finally {
			return CommonUtils.getResponse(response, MediaType.APPLICATION_JSON);
		} 
	}
	
	
	@RequestMapping(value = "${dummy_oms_url}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> sendResponse(@RequestBody String token) {
		logger.info("sfssadasd");
		
		String result="Success";
		String userId="14";
		Map<String,String> mapResponse = null;
		if(token!=null && !token.isEmpty()){
		
	    mapResponse=new HashMap<String, String>();
		mapResponse.put("Result", result);
		mapResponse.put("UserId", userId);
		}
		
		return CommonUtils.getResponse(mapResponse, MediaType.APPLICATION_JSON);
	}
	

}
