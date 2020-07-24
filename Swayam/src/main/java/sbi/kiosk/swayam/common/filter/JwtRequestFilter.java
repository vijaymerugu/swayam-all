package sbi.kiosk.swayam.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.SignatureException;
import sbi.kiosk.swayam.common.controller.LoginController;
import sbi.kiosk.swayam.common.service.MyUserDetailsService;
import sbi.kiosk.swayam.common.utils.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Autowired
	private MyUserDetailsService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		/*
		 * final String authorizationHeader =
		 * request.getHeader("Authorization"); System.out.println("header " +
		 * authorizationHeader);
		 */
		logger.info("Inside the JwtRequest Filter");

		final String QueryString = request.getQueryString();
		// System.out.println("final queryString " + QueryString);

		String username = null;
		String jwt = null;

		/*
		 * if (authorizationHeader != null &&
		 * authorizationHeader.startsWith("Bearer ")) {
		 * 
		 * jwt = authorizationHeader.substring(7); username =
		 * jwtUtil.extractUsername(jwt);
		 * 
		 * }
		 */

		if (QueryString != null && QueryString.contains("token=")) {

			jwt = QueryString.substring(6);
			logger.info("Token = " + jwt);

			try {

				username = jwtUtil.extractUsername(jwt);

			} catch (SignatureException e) {

				// System.out.println("Invlid JWT token");

			}

		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}

		filterChain.doFilter(request, response);

	}

}
