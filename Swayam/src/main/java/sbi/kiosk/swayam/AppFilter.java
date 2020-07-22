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
        ///res.addHeader("Expires", "-1" );
        res.addHeader("Pragma", "no-cache" );	
        res.addHeader("Cache-control", "no-cache, no-store, must-revalidate");
        res.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "authorization, content-type, xsrf-token");

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
