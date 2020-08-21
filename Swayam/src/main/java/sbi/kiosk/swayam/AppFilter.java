package sbi.kiosk.swayam;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class AppFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
	FilterChain chain) throws IOException, ServletException {
	// TODO Auto-generated method stub
	HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse res = (HttpServletResponse) response;
	        res.setHeader("X-FRAME-OPTIONS", "DENY");
	        res.addHeader("Expires", "0" );
	        res.addHeader("Pragma", "no-cache" );
	        res.addHeader("Cache-control", "no-cache, no-store, max-age=0, must-revalidate");
	        res.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
	        res.setHeader("Access-Control-Max-Age", "3600");
	        res.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");
	       
	        res.setHeader("Strict-Transport-Security","max-age=31536000 ; includeSubDomains");
	        res.setHeader("X-Content-Type-Options", "nosniff");        
	        res.setHeader("X-XSS-Protection", "1; mode=block");
	        //res.setHeader("Content-Security-Policy", "default-src 'self'");
	        String sessionid = req.getSession().getId();
	        
	        
	        if ("POST".equals(req.getMethod()) && "/getToken".equals(req.getServletPath())){
	        	Cookie myCookie =  new Cookie("JSESSIONID", sessionid);
	   		 	myCookie.setPath("/getToken");
	   		 	myCookie.setDomain(req.getServerName());	
	   		    myCookie.setSecure(true);
	   		    myCookie.setHttpOnly(true);
	   		 	res.addCookie(myCookie);	
	   		    res.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly; Secure; Path=/getToken; Domain= " + req.getServerName() + "");
	        }
	        else {
	        	res.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly; Secure; Domain= " + req.getServerName() + "");
	        }	        
	        //res.setHeader("Refresh", "60; URL=/SMT/");

	        //System.out.println(
	          //"Logging Request  {} : {}");
	        	//chain.doFilter(request, response);
	        //System.out.println(
	          //"Logging Response :{}");
	        if ("POST".equals(req.getMethod()) && !"/getToken".equals(req.getServletPath())){
	        	
		        String csrfToken = req.getHeader("X-CSRF-TOKEN");
		        if (csrfToken ==null || csrfToken.isEmpty()) {
		             res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		             req.getSession().invalidate();
	                 req.getRequestDispatcher("/").forward(request, response);
		        }	        
		        else { 
	                if(req.getSession() !=null && csrfToken.equals(req.getSession().getAttribute("csrfToken"))) {
	            		req.getSession().setAttribute("csrfToken", UUID.randomUUID().toString());
	            		chain.doFilter(request, response);
	            	}
	                else {
	                	res.setStatus(HttpServletResponse.SC_FORBIDDEN);
	                	req.getSession().invalidate();
	                	req.getRequestDispatcher("/").forward(request, response);             	
	                }
		        }                  
            } 
	        else {
            	chain.doFilter(request, response);
            }   

	}

	@Override
	public void destroy() {
	// TODO Auto-generated method stub

	}

}
