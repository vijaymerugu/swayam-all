package sbi.kiosk.swayam;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
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
	       // res.setHeader("SET-COOKIE", "JSESSIONID=" + sessionid + "; HttpOnly; Secure");
	        //res.setHeader("Refresh", "60; URL=/SMT/");

	        //System.out.println(
	          //"Logging Request  {} : {}");
	        chain.doFilter(request, response);
	        //System.out.println(
	          //"Logging Response :{}");

	}

	@Override
	public void destroy() {
	// TODO Auto-generated method stub

	}

}
