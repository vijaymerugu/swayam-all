
package sbi.kiosk.swayam.common.controller;


import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sbi.kiosk.swayam.common.entity.AuditLogger;
import sbi.kiosk.swayam.common.repository.AuditInsertRepository;
import sbi.kiosk.swayam.common.service.LoginService;
import sbi.kiosk.swayam.common.service.MyUserDetailsService;
import sbi.kiosk.swayam.common.utils.JwtUtil;
import sbi.kiosk.swayam.healthmonitoring.model.AuthenticationReponse;

@RestController
public class JwtController {
	Logger logger = LoggerFactory.getLogger(JwtController.class);

	@Autowired
	private AuthenticationManager authenticationManger;

	@Autowired
	private MyUserDetailsService userDetailService;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	AuditInsertRepository audit;
	
//	@Autowired
//	AuthenticationHeaderRequest authenticationRequest;

	/*@RequestMapping({ "/hello" })
	public String hello(HttpServletRequest request) {

		return "Hello World";

	}*/
	
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/getToken", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTocken(@RequestHeader(value="USER_ID") String userID,AuditLogger auditLogger,HttpServletRequest req,HttpServletResponse res)
			throws Exception {

		String jwt = null;
	
		auditLogger.setPath("/getToken");
		auditLogger.setUser_Id(userID);
		SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.util.Date date = new java.util.Date();
			
		auditLogger.setSession_Date(formatter.format(date));
		
		auditLogger.setStatus("Success");
		auditLogger.setToken(jwt);
		try {

			authenticationManger
					.authenticate(new UsernamePasswordAuthenticationToken(userID, ""));

			final UserDetails userDetails = userDetailService.loadUserByUsername(userID);
			jwt = jwtTokenUtil.generateToken(userDetails);
			auditLogger.setToken(jwt);

		} catch (InternalAuthenticationServiceException e) {
			logger.info("Failed", e.getLocalizedMessage());
		
	
			auditLogger.setStatus("Failed");
			auditLogger.setToken(jwt);
			audit.save(auditLogger);
			return ResponseEntity.ok(new AuthenticationReponse("FAIL", jwt));

		}catch (Exception e) {
		
			logger.info("Failed "+ e.getLocalizedMessage());
		}

		audit.save(auditLogger);
		
		Cookie myCookie =  new Cookie("JSESSIONID", req.getSession().getId());
		 myCookie.setPath("/getToken");
		 myCookie.setDomain(req.getServerName());		 
		res.addCookie(myCookie);
		
		return ResponseEntity.ok(new AuthenticationReponse("SUCCESS", jwt));

	}
	
	
	

	/*@RequestMapping(value = "/getToken", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationTocken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		String jwt = null;

		try {

			authenticationManger
					.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), ""));

			final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
			jwt = jwtTokenUtil.generateToken(userDetails);
			System.out.println("get tolen : " + jwt);

		} catch (InternalAuthenticationServiceException e) {
			logger.info("Failed", e.getLocalizedMessage());

			return ResponseEntity.ok(new AuthenticationReponse("FAIL", jwt));

		}catch (Exception e) {
			logger.info("Failed", e.getLocalizedMessage());
		}

		return ResponseEntity.ok(new AuthenticationReponse("SUCESS", jwt));

	}*/

}
