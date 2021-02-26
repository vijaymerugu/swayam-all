package sbi.kiosk.swayam.common.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import sbi.kiosk.swayam.common.dto.MenuMasterDto;
import sbi.kiosk.swayam.common.dto.RequestResponseLogDto;
import sbi.kiosk.swayam.common.dto.ResponseDto;
import sbi.kiosk.swayam.common.dto.UserDto;
import sbi.kiosk.swayam.common.entity.AuditLogger;
import sbi.kiosk.swayam.common.entity.CommonUrl;
import sbi.kiosk.swayam.common.repository.AuditInsertRepository;
import sbi.kiosk.swayam.common.repository.CommonUrlConfigRepository;
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
	
	@Autowired
	HttpSession httpSession;
	
	@Autowired
	AuditInsertRepository audit;
	@Autowired
	CommonUrlConfigRepository commonUrlConfigRepo;
	//@Autowired
	//private JwtUtil jwtTokenUtil;
	
	
	
	
	 static {
	        // this part is needed cause Lebocoin has invalid SSL certificate, that cannot be normally processed by Java
	        TrustManager[] trustAllCertificates = new TrustManager[]{
	                new X509TrustManager() {
	                    @Override
	                    public X509Certificate[] getAcceptedIssuers() {
	                        return null; // Not relevant.
	                    }

	                    @Override
	                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
	                        // Do nothing. Just allow them all.
	                    }

	                    @Override
	                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
	                        // Do nothing. Just allow them all.
	                    }
	                }
	        };

	        HostnameVerifier trustAllHostnames = new HostnameVerifier() {
	            @Override
	            public boolean verify(String hostname, SSLSession session) {
	                return true; // Just allow them all.
	            }
	        };

	        try {
	            System.setProperty("jsse.enableSNIExtension", "false");
	            SSLContext sc = SSLContext.getInstance("SSL");
	            sc.init(null, trustAllCertificates, new SecureRandom());
	            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	            HttpsURLConnection.setDefaultHostnameVerifier(trustAllHostnames);
	        } catch (GeneralSecurityException e) {
	            throw new ExceptionInInitializerError(e);
	        }
	    }
	
	// comments 27-Jan-2021 this is method for jwt token
	
	/*@SuppressWarnings("deprecation")
	@GetMapping("authenticateUser")
	@PostAuthorize("hasPermission('login','READ')")
	public ModelAndView home(@RequestParam(value="token")String token, HttpSession session,AuditLogger auditLogger,ModelAndView mav) {
		
		//logger.info("Inside /authenticateUser?token"+token );
		
		try {
		String pfId = jwtTokenUtil.extractUsername(token);
	//	logger.info("Inside /authenticateUser?token"+token+ " USER_ID "+pfId);
		UserDto userObj = loginService.getRoleByUsername(pfId);
		session.setAttribute("pfId", userObj.getPfId());
		session.setAttribute("userObj", userObj);
		session.setAttribute("csrfToken", UUID.randomUUID().toString());
	//	logger.info("Session Val" + session.getAttribute("pfId"));
		
		auditLogger.setPath("/authenticateUser");
		auditLogger.setUser_Id(pfId);
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.util.Date date = new java.util.Date();
		//System.out.println("date "+ formatter.format(date));
		
		auditLogger.setSession_Date(formatter.format(date));
		
		if(pfId!=null){
			auditLogger.setStatus("Success");
		}else{
			auditLogger.setStatus("failed");
		}
		
		auditLogger.setToken(token);
		//audit.save(auditLogger);
		mav.setViewName("redirect:/home");
		}catch(Exception e) {
			logger.error("Invalid Token Exception():: ",e,e.getMessage());
			mav.addObject("commonError", "Bad Request");
			mav.setViewName("error");
		}
		return mav;
	}*/
	
	
	@SuppressWarnings({ "finally", "restriction" })
	@RequestMapping(value = "authenticateUser", method ={ RequestMethod.GET,RequestMethod.POST})
	@PostAuthorize("hasPermission('login','READ')")
	public ModelAndView  createAuthentication(@RequestParam("Token") String Token, HttpServletResponse res, final RedirectAttributes redirectAttributes, HttpSession session, AuditLogger auditLogger,
			ModelAndView mav) throws Exception {
		//ResponseEntity<Object>
		String output = "";
		ResponseDto response = null;
		String userId =null;
		String result =null;
		try {
			String dbOms_url=commonUrlConfigRepo.findOmsUrl();
			CommonUrl certificatePaths=commonUrlConfigRepo.findURL();
			
			String certificatePath=certificatePaths.getSbiUrl();
			System.setProperty("javax.net.ssl.trustStore",certificatePath);
			  // comment out below line
			 // System.setProperty("javax.net.ssl.trustStore","trust_store/keystore.jks");
			  System.setProperty("javax.net.ssl.trustStorePassword", "123456");
			  //System.setProperty("javax.net.debug", "all");

			
				boolean startIp=dbOms_url.startsWith("https");
				logger.info("startIp::"+ startIp);
			  if(startIp==true){
			
			Gson gson = new Gson();
			//logger.info("createAuthentication() Started:::::: "+dbOms_url);
			JSONObject jsonResp=new JSONObject();
			if ((Token != null && !Token.isEmpty()) && (!Token.equals("") && !Token.equals("null"))) {
				//logger.info("createAuthentication()==1111");
				jsonResp=CommonUtils.decoded(Token);
				//logger.info("createAuthentication()==11112");
				String header= (String) jsonResp.get("header");
				String payload= (String) jsonResp.get("payload");
				//logger.info("Result userId::" + result);
				//logger.info("Result Header::" + header);
				//logger.info("Result Payload::" + payload);
				
				 HashMap<String,String> hashMap = new ObjectMapper().readValue(payload, HashMap.class);
				// logger.info("Result hashMap::" + hashMap);
				 String tokenUserId=hashMap.get("jti");
				// logger.info("Result tokenUserId::" +tokenUserId );
				 
				StringBuilder postData = new StringBuilder();
				postData.append(URLEncoder.encode("Token", "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(String.valueOf(Token), "UTF-8"));
				byte[] postDataBytes = postData.toString().getBytes("UTF-8");
                    
				//HttpsURLConnection conn =null;
				HttpsURLConnection conn =null;
					try{	
						///logger.info("postData==1="+new  sun.net.www.protocol.https.Handler());
						conn=  (HttpsURLConnection) new URL(null,dbOms_url,new  sun.net.www.protocol.https.Handler()).openConnection();
					//	logger.info("postData==2="+new  sun.net.www.protocol.https.Handler());
						//conn=  (HttpURLConnection) new URL(dbOms_url).openConnection();
					}catch(Exception t){
						logger.info("Inside connection url not stablish="+t.getMessage());
					}
				conn.setRequestMethod("POST");
				//conn.setRequestProperty("Content-Type","application/json");
				conn.setRequestProperty("Content-Type", "application/json"); //NEW LINE ADD
				//logger.info("postData==91=");
				conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length)); //NEW LINE ADD
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.getOutputStream().write(postDataBytes);
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				if ((output = br.readLine()) != null) {
					Map map = gson.fromJson(output, Map.class);
					userId= (String) map.get("userId");
					result = (String) map.get("result");
					logger.info("Result userId::" + userId);
					logger.info("Result Status::" + result);
				}
				//userId="974325";
				//result="SUCCESS";
					response = new ResponseDto();
					RequestResponseLogDto reqRespLogDto = new RequestResponseLogDto();
					reqRespLogDto.setUserId(userId);
					reqRespLogDto.setToken(Token);
					reqRespLogDto.setRequest(Token);
					reqRespLogDto.setUrl(dbOms_url);
					reqRespLogDto.setSuccess(result);
					reqRespLogDto.setResponse(output);
					
					//if(tokenUserId!=null && userId!=null && userId.equals(tokenUserId)){
					//}
					
					if (userId != null && !userId.isEmpty() && result != null && result.equalsIgnoreCase("SUCCESS") && tokenUserId!=null && userId.equals(tokenUserId)) {

						// check userId in db
						String dbPfId = userRepo.findIdByPfId(userId);
						//logger.info("result found::" + result);
						response.setPfId(dbPfId);
						response.setResponse(result);
						if (userId!=null && userId.equals(dbPfId)) { // call login method
							//logger.info("Inside /authenticateUser?token=" + Token + " userId " + userId);
							UserDto userObj = loginService.getRoleByUsername(userId);
							session.setAttribute("pfId", userObj.getPfId());
							session.setAttribute("userObj", userObj);
							//logger.info("Session Val"+ session.getAttribute("pfId"));
							auditLogger.setPath("/authenticateUser");
							auditLogger.setUser_Id(userId);
							SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
							java.util.Date date = new java.util.Date();
							auditLogger.setSession_Date(formatter.format(date));
						    auditLogger.setStatus("Success");
							auditLogger.setToken(Token);
							audit.save(auditLogger);
							//logger.info("Before home::" + result);
							mav = new ModelAndView("redirect:/home");
							//logger.info("After home::" + result);
						} else {
							response.setResponse("UserId does not Exist");
							mav = new ModelAndView("redirect:/errorCode1");
						}
					} else {
						//response.setResponse("Invalid Respons");
						auditLogger.setPath("/authenticateUser");
						auditLogger.setUser_Id(userId);
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
						java.util.Date date = new java.util.Date();
						auditLogger.setSession_Date(formatter.format(date));
					    auditLogger.setStatus("FAIL");
						auditLogger.setToken(Token);
						audit.save(auditLogger);
						response.setResponse("Failed");
						mav = new ModelAndView("redirect:/errorCode4");
					
					}
			}else{
				mav = new ModelAndView("redirect:/errorCode2");
			}
			
			  }else{
				 // logger.info("Http Local Host::11" + dbOms_url);
				  HttpURLConnection conn =null;
				  byte[] postDataBytes = null;
				  Gson gson = new Gson();
					try{
						StringBuilder postData = new StringBuilder();
						postData.append(URLEncoder.encode("Token", "UTF-8"));
						postData.append('=');
						postData.append(URLEncoder.encode(String.valueOf(Token), "UTF-8"));
						 postDataBytes = postData.toString().getBytes("UTF-8");
					//	logger.info("postData==1="+new  sun.net.www.protocol.https.Handler());
						//conn=  (HttpURLConnection) new URL(null,dbOms_url,new  sun.net.www.protocol.https.Handler()).openConnection();
						//logger.info("postData==2="+new  sun.net.www.protocol.https.Handler());
						conn=  (HttpURLConnection) new URL(dbOms_url).openConnection();
					}catch(Exception t){
						logger.info("Inside connection url not stablish="+t.getMessage());
					}
				conn.setRequestMethod("POST");
				//conn.setRequestProperty("Content-Type","application/json");
				conn.setRequestProperty("Content-Type", "application/json"); //NEW LINE ADD
				conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length)); //NEW LINE ADD
				conn.setDoOutput(true);
				conn.setDoInput(true);
				conn.getOutputStream().write(postDataBytes);
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
				if ((output = br.readLine()) != null) {
					Map map = gson.fromJson(output, Map.class);
					userId= (String) map.get("userId");
					result = (String) map.get("result");
					logger.info("Result userId::" + userId);
					logger.info("Result Status::" + result);
				}
				//userId="974325";
				//result="SUCCESS";
					response = new ResponseDto();
					RequestResponseLogDto reqRespLogDto = new RequestResponseLogDto();
					reqRespLogDto.setUserId(userId);
					reqRespLogDto.setToken(Token);
					reqRespLogDto.setRequest(Token);
					reqRespLogDto.setUrl(dbOms_url);
					reqRespLogDto.setSuccess(result);
					reqRespLogDto.setResponse(output);
					
					//if(tokenUserId!=null && userId!=null && userId.equals(tokenUserId)){
					//}
					
					if (userId != null && !userId.isEmpty() && result != null && result.equalsIgnoreCase("SUCCESS")) {

						// check userId in db
						String dbPfId = userRepo.findIdByPfId(userId);
						//logger.info("result found::" + result);
						response.setPfId(dbPfId);
						response.setResponse(result);
						if (userId!=null && userId.equals(dbPfId)) { // call login method
							//logger.info("Inside /authenticateUser?token=" + Token + " userId " + userId);
							UserDto userObj = loginService.getRoleByUsername(userId);
							session.setAttribute("pfId", userObj.getPfId());
							session.setAttribute("userObj", userObj);
							//logger.info("Session Val"+ session.getAttribute("pfId"));
							auditLogger.setPath("/authenticateUser");
							auditLogger.setUser_Id(userId);
							SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
							java.util.Date date = new java.util.Date();
							auditLogger.setSession_Date(formatter.format(date));
						    auditLogger.setStatus("Success");
							auditLogger.setToken(Token);
							audit.save(auditLogger);
						//	logger.info("Before home::" + result);
							mav = new ModelAndView("redirect:/home");
						//	logger.info("After home::" + result);
						} else {
							response.setResponse("UserId does not Exist");
							mav = new ModelAndView("redirect:/errorCode1");
						}
					} else {
						//response.setResponse("Invalid Respons");
						auditLogger.setPath("/authenticateUser");
						auditLogger.setUser_Id(userId);
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
						java.util.Date date = new java.util.Date();
						auditLogger.setSession_Date(formatter.format(date));
					    auditLogger.setStatus("FAIL");
						auditLogger.setToken(Token);
						audit.save(auditLogger);
						response.setResponse("Failed");
						mav = new ModelAndView("redirect:/errorCode4");
					}
			  }
			
		} catch (Exception e) {
		//	e.printStackTrace();
			logger.info("Exception");
			//logger.error("Exception():: ",e,e.getMessage());
			 mav = new ModelAndView("redirect:/errorCode3");
		}
		//logger.info("Result Status END()...." + result);
		return	mav;
	}
	
	@RequestMapping(value = "errorCode1", method = RequestMethod.GET)
	public ModelAndView redirectOmsError() {

		ModelAndView mav = new ModelAndView("omsError");
		mav.addObject("commonError","Invalid userId");
		return mav;

	}
	
	@RequestMapping(value = "errorCode2", method = RequestMethod.GET)
	public ModelAndView redirectOmsError2() {

		ModelAndView mav = new ModelAndView("omsError");
		mav.addObject("commonError", "token is empty.");
		return mav;

	}

	@RequestMapping(value = "errorCode3", method = RequestMethod.GET)
	public ModelAndView redirectOmsError3() {
		ModelAndView mav = new ModelAndView("omsError");
		mav.addObject("commonError", "due to Server Error Please try to after some time.");
		return mav;

	}
	
	@RequestMapping(value = "errorCode4", method = RequestMethod.GET)
	public ModelAndView redirectOmsError4() {
		ModelAndView mav = new ModelAndView("omsError");
		mav.addObject("commonError","Invalid token/token expired!");
		return mav;

	}
	
	
	/*
	 * @RequestMapping(value = "/home", method = RequestMethod.GET)
	 * 
	 * @PostAuthorize("hasPermission('login','READ')") public ModelAndView
	 * redirect() { ModelAndView mav = new ModelAndView("home"); return mav;
	 * 
	 * }
	 */
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@PostAuthorize("hasPermission('login','READ')")
	   public ModelAndView redirect() {
		UserDto user = (UserDto) httpSession.getAttribute("userObj");
		 ModelAndView mav = new ModelAndView("home");
		 String role = userRepo.findRoleByPfId(user.getPfId());
		 
		// System.out.println("Role " + role);
		 
		 if(role.equalsIgnoreCase("BM")) {
			 mav.addObject("suburl", "bp/billingPenalty");	 
		 }else if(role.equalsIgnoreCase("BC")){
			 mav.addObject("suburl", "bp/billingPenalty");	 
		 }else if(role.equalsIgnoreCase("CC")){
			 mav.addObject("suburl", "da/availability");	 
		 }else if(role.equalsIgnoreCase("SA")){
			 mav.addObject("suburl", "km/userList");	 
		 }else if(role.equalsIgnoreCase("LA")){
			 mav.addObject("suburl", "km/userList");	 
		 }else if(role.equalsIgnoreCase("CMS")){
			 mav.addObject("suburl", "ts/terminalStatus");	 
		 }else if(role.equalsIgnoreCase("CMF")){
			 mav.addObject("suburl", "ts/terminalStatus");	 
		 }else if(role.equalsIgnoreCase("C")){
			 mav.addObject("suburl", "da/cumulativeCircleData");	 
		 }
		 
		 
		 
		// System.out.println("Inside /home method ---- login..... ");
		 
		// mav.setViewName("billingPenalty");
		 return mav;
	    
	   }
	
	/*
	 * @RequestMapping(value="login", method=RequestMethod.POST)
	 * 
	 * @PostAuthorize("hasPermission('login','READ')") public ModelAndView
	 * login(@RequestParam("pfId") String pfId, HttpSession session) {
	 * 
	 * UserDto userObj = loginService.getRoleByUsername(pfId);
	 * 
	 * session.setAttribute("pfId", userObj.getPfId());
	 * session.setAttribute("userObj", userObj);
	 * 
	 * 
	 * logger.info("Session Val"+ session.getAttribute("pfId")); ModelAndView mav =
	 * new ModelAndView("home"); return mav; }
	 * 
	 * @RequestMapping(value="logout")
	 * 
	 * @PreAuthorize("hasPermission('logout','READ')") public ModelAndView
	 * logout(ModelAndView model,HttpSession session) {
	 * 
	 * session.invalidate();
	 * 
	 * model.setViewName("redirect:https://adfs.sbi.co.in/adfs/ls/?wa=wsignout1.0");
	 * return model; }
	 */
	
	
	  @RequestMapping(value="logout")
	  @PreAuthorize("hasPermission('logout','READ')") 
	  public ModelAndView logout(ModelAndView model,HttpSession session,AuditLogger auditLogger) {
		UserDto userObj = (UserDto) session.getAttribute("userObj");
		auditLogger.setUser_Id(userObj.getPfId());
		auditLogger.setStatus("LogOut");
		auditLogger.setPath("/logout");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.util.Date date = new java.util.Date();
		auditLogger.setSession_Date(formatter.format(date));
		audit.save(auditLogger);
	    session.invalidate();
	    logger.info("SuccessFully  LogOut:::"+userObj.getPfId());
	    model.setViewName("redirect:https://adfs.sbi.co.in/adfs/ls/?wa=wsignout1.0");
	  return model; 
	  }
	 
	
	@RequestMapping(value="common/menu", method=RequestMethod.GET)
	@PreAuthorize("hasPermission('commonMenu','READ')")
	public List<MenuMasterDto> getMenu(HttpSession session) {		
		UserDto userObj =(UserDto) session.getAttribute("userObj");
		//session.setAttribute("username", username);
//		logger.info("Session Val1111::: "+ session.getAttribute("pfId"));
		return loginService.getMenusByUserRole(userObj.getRole());
		//ModelAndView mav = new ModelAndView("home");
		//return mav;		
	}
	
	@RequestMapping("summary")
	public ModelAndView summary() {
		ModelAndView mav = new ModelAndView("userlist");
		return mav;
	}
	

	/*
	 * @SuppressWarnings("finally")
	 * 
	 * @RequestMapping(value = "${sbi_url}", method = RequestMethod.GET, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Object>
	 * createAuthentication(@RequestParam("token") String token,HttpServletResponse
	 * res) throws Exception { String output=""; ResponseDto response =null;
	 * 
	 * try {
	 * 
	 * logger.info("oms_url post 1 request:::: "+oms_url);
	 * 
	 * System.err.println("inside createAuthentication token "+token);
	 * if((token!=null && !token.isEmpty()) && (!token.equals("") &&
	 * !token.equals("null"))){
	 * 
	 * URL url = new URL(oms_url); logger.info("oms_url post request:::: "+oms_url);
	 * Map<String,Object> params = new LinkedHashMap<>(); params.put("token",
	 * token); StringBuilder postData = new StringBuilder();
	 * 
	 * for (Map.Entry<String,Object> param : params.entrySet()) { if
	 * (postData.length() != 0) postData.append('&');
	 * postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
	 * postData.append('=');
	 * postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
	 * "UTF-8")); } byte[] postDataBytes = postData.toString().getBytes("UTF-8");
	 * 
	 * HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	 * conn.setRequestMethod("POST"); conn.setRequestProperty("Content-Type",
	 * "application/json"); conn.setRequestProperty("Content-Length",
	 * String.valueOf(postDataBytes.length)); conn.setDoOutput(true);
	 * conn.getOutputStream().write(postDataBytes);
	 * 
	 * BufferedReader br = new BufferedReader(new
	 * InputStreamReader(conn.getInputStream(), "UTF-8"));
	 * 
	 * 
	 * if ((output = br.readLine()) != null) { Gson gson = new Gson();
	 * logger.info("response1==="+output); Map map = gson.fromJson(output,
	 * Map.class); logger.info("response2==="+map);
	 * 
	 * String userId=(String) map.get("UserId"); String result=(String)
	 * map.get("Result"); logger.info("response3==="+result);
	 * 
	 * response =new ResponseDto(); RequestResponseLogDto reqRespLogDto=new
	 * RequestResponseLogDto();
	 * 
	 * reqRespLogDto.setUserId(userId); reqRespLogDto.setToken(token);
	 * reqRespLogDto.setRequest(token); reqRespLogDto.setUrl(oms_url);
	 * reqRespLogDto.setSuccess(result); reqRespLogDto.setResponse(output);
	 * 
	 * 
	 * loginService.saveReqAndResponse(reqRespLogDto);
	 * 
	 * 
	 * if(userId!=null && !userId.isEmpty() && result!=null &&
	 * result.equalsIgnoreCase("Success")){
	 * 
	 * 
	 * // check userId in db String dbPfId=userRepo.findIdByPfId(userId);
	 * logger.info("dbPfId=="+dbPfId); response.setPfId(dbPfId);
	 * response.setResponse(result); if(userId.equals(dbPfId)){ // call login method
	 * 
	 * //ModelAndView mav = new ModelAndView("login");
	 * //mav.setViewName("redirect:/login");
	 * 
	 * //res.sendRedirect("/login");
	 * 
	 * 
	 * }else{ response.setResponse("UserId is not Exist"); } }else{
	 * response.setResponse("Invalid Respons"); } } }
	 * 
	 * 
	 * } catch (Exception e) {
	 * logger.error("Exception "+ExceptionConstants.AUTHENTICATION_ERROR);
	 * 
	 * }finally { return CommonUtils.getResponse(response,
	 * MediaType.APPLICATION_JSON); } }
	 * 
	 * 
	 * @RequestMapping(value = "${dummy_oms_url}", method = RequestMethod.POST,
	 * produces = MediaType.APPLICATION_JSON_VALUE,consumes =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Object>
	 * sendResponse(@RequestBody String token) { logger.info("sfssadasd");
	 * 
	 * String result="Success"; String userId="14"; Map<String,String> mapResponse =
	 * null; if(token!=null && !token.isEmpty()){
	 * 
	 * mapResponse=new HashMap<String, String>(); mapResponse.put("Result", result);
	 * mapResponse.put("UserId", userId); }
	 * 
	 * return CommonUtils.getResponse(mapResponse, MediaType.APPLICATION_JSON); }
	 */

}
