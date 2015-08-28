package com.moderndrummer.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.moderndrummer.model.Member;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

@Component("authenticationInterceptor")
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String uri = request.getRequestURI();
		if (!uri.endsWith("/login") && !uri.endsWith("/logout") && !uri.endsWith("/register")
				&& !uri.endsWith(".png")) {
			Member userData = (Member) request.getSession().getAttribute("loggedUser");
			if (userData == null) {
				response.sendRedirect("/ModernDrummer/login");
				return false;
			}
		}
		return true;
	}
}
