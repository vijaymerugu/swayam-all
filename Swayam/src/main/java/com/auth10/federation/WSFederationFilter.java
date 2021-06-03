package com.auth10.federation;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WSFederationFilter implements Filter {

	private static final String PRINCIPAL_SESSION_VARIABLE = "FederatedPrincipal";

	private String loginPage;
	private String excludedUrlsRegex;
	private String excludedUrlsRegex_JS;
	private String excludedUrlsRegex_CSS;
	
	final Logger logger = LoggerFactory.getLogger(WSFederationFilter.class);

	public WSFederationFilter() {
		logger.debug("constructing");
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		logger.debug("initializing");
		this.loginPage = config.getInitParameter("login-page-url");
		this.excludedUrlsRegex = config.getInitParameter("exclude-urls-regex");
		this.excludedUrlsRegex_JS = config.getInitParameter("exclude-urls-regex-js");
		this.excludedUrlsRegex_CSS = config.getInitParameter("exclude-urls-regex-css");
		System.out.println("excludedUrlsRegex <><><> " + excludedUrlsRegex);
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		/*
		 * String url = null; String queryString = null; if (request instanceof
		 * HttpServletRequest) {
		 * 
		 * url = ((HttpServletRequest)request).getRequestURL().toString(); queryString =
		 * ((HttpServletRequest)request).getQueryString(); }
		 * 
		 * logger.info("Request url " + url); logger.info("Request Query String " +
		 * queryString);
		 */

		
		
		/*
		 * if(url.contains("authenticateUser")) { chain.doFilter(request, response);
		 * 
		 * }else {
		 */
		
		logger.debug("filtering");

		FederatedPrincipal principal = null;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path =  httpRequest.getRequestURL().toString();

		boolean checkSession = sessionTokenExists(httpRequest);
		System.out.println("checkSession <><><> " + checkSession);
		
			// is the request is a token?
			if(checkSession == false){ // Condition Added By Deepak 
				if (this.isSignInResponse(httpRequest)) {
					logger.debug("authenticating with token");
					principal = this.authenticateWithToken(httpRequest, httpResponse);
					this.writeSessionToken(httpRequest, principal);
					this.redirectToOriginalUrl(httpRequest, httpResponse);
				}
			}

			// is principal in session?
			if (principal == null && this.sessionTokenExists(httpRequest)) {
				logger.debug("authenticating with session token");
				principal = this.authenticateWithSessionToken(httpRequest,
						httpResponse);
			}

			// if not authenticated at this point, redirect to login page
			System.out.println("httpRequest.getRequestURL().toString() <><> " + httpRequest.getRequestURL().toString());
			System.out.println("Matcher <><><> " + Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find());
			
			boolean excludedUrl = httpRequest.getRequestURL().toString().contains(this.loginPage) 
								  || (this.excludedUrlsRegex != null
							      && !this.excludedUrlsRegex.isEmpty() 
							      && Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find()); 
//							      || (this.excludedUrlsRegex != null
//									  && !this.excludedUrlsRegex.isEmpty() 
//									  && Pattern.compile(this.excludedUrlsRegex).matcher(httpRequest.getRequestURL().toString()).find());

			System.out.println("excludedUrl <><><> " + excludedUrl);
			if(excludedUrl){
				System.out.println("excludedUrl <><> " + excludedUrl);
			}
			
			if (!excludedUrl && principal == null) {
				System.out.println("INSIDE  Condition 1");
				if (!FederatedConfiguration.getInstance().getEnableManualRedirect()) {
					System.out.println("INSIDE  Condition 2");
					logger.debug("redirecting to identity provider");
					this.redirectToIdentityProvider(httpRequest, httpResponse);
				} else {
					System.out.println("INSIDE  Condition 3");
					logger.debug("redirecting to login page");
					this.redirectToLoginPage(httpRequest, httpResponse);
				}

				return;
			}
			logger.debug("principal=" + principal);
			System.out.println("excludedUrlsRegex <><> " + excludedUrlsRegex);
			System.out.println("path.toLowerCase() <><> " + path.toLowerCase());
			boolean pathVal =  path.toLowerCase().contains(excludedUrlsRegex);
			System.out.println("pathVal <><> " + pathVal);
				chain.doFilter(new FederatedHttpServletRequest(httpRequest, principal),response);
//			}
			
	//}
		
	}

	protected void redirectToLoginPage(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String encodedReturnUrl = URLUTF8Encoder
				.encode(getRequestPathAndQuery(httpRequest));
		String redirect = this.loginPage + "?returnUrl=" + encodedReturnUrl;
		httpResponse.setHeader("Location", redirect);
		httpResponse.setStatus(302);
	}

	protected void redirectToIdentityProvider(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String wctx = getRequestPathAndQuery(httpRequest);
		String redirect = FederatedLoginManager.getFederatedLoginUrl(wctx);
		httpResponse.setHeader("Location", redirect);
		httpResponse.setStatus(302);
	}

	protected void redirectToOriginalUrl(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		String wctx = httpRequest.getParameter("wctx");
		if (wctx != null) {
			httpResponse.setHeader("Location", wctx);
			httpResponse.setStatus(302);
		}
	}

	protected Boolean isSignInResponse(HttpServletRequest request) {
	System.out.println("request.getMethod().equals(POST) <><><> " + request.getMethod().equals("POST"));
	System.out.println("request.getParameter(wa) <><><> " + request.getParameter("wa"));
	System.out.println("request.getParameter(wresult) <><><> " + request.getParameter("wresult"));
	
//		if(!request.getParameter("wa").equalsIgnoreCase("null")){
//			System.out.println("HIISJHGFHJGFHJGFHJFSGJHGF");
//		}
		if (request.getMethod().equals("POST")
				&& request.getParameter("wa").equals("wsignin1.0")
				&& request.getParameter("wresult") != null) {
			return true;
		}

		return false;
	}

	protected Boolean sessionTokenExists(HttpServletRequest request) {
		// this could use signed cookies instead of sessions
		return request.getSession().getAttribute(PRINCIPAL_SESSION_VARIABLE) != null;
	}

	protected FederatedPrincipal authenticateWithSessionToken(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		return (FederatedPrincipal) request.getSession().getAttribute(
				PRINCIPAL_SESSION_VARIABLE);
	}

	protected void writeSessionToken(HttpServletRequest request,
			FederatedPrincipal principal) throws IOException {
		request.getSession()
				.setAttribute(PRINCIPAL_SESSION_VARIABLE, principal);
	}

	protected FederatedPrincipal authenticateWithToken(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String token = request.getParameter("wresult").toString();

		if (token == null) {
			sendError(request, response, 400,
					"You were supposed to send a wresult parameter with a token");
		}

		FederatedLoginManager loginManager = FederatedLoginManager.fromRequest(
				request, null);

		try {
			
			System.out.println("token Value <><><><> " + token);
			FederatedPrincipal principal = loginManager.authenticate(token,response);
			System.out.println("principal <<><><> " + principal);
			System.out.println("principal <<><><> " + principal);
			return principal;
		} catch (FederationException e) {
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			sendError(request, response, 500,
					"Oops an error occurred validating the token.");
		}

		return null;
	}

	private void sendError(HttpServletRequest request,
			HttpServletResponse response, int errorNum, String message)
			throws IOException {
		// create the session to avoid IllegalStateException: Cannot create
		// a session after the response has been committed.
		request.getSession();
		logger.warn("response " + errorNum + ": " + message);
		response.sendError(errorNum, message);
	}

	@Override
	public void destroy() {
	}

	private static String getRequestPathAndQuery(HttpServletRequest req) {
		String reqUri = req.getRequestURI().toString();
		String queryString = req.getQueryString();
		if (queryString != null) {
			reqUri += "?" + queryString;
		}

		return reqUri;
	}
}
