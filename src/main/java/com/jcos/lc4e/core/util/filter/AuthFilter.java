package com.jcos.lc4e.core.util.filter;

import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jcos.lc4e.core.util.annotation.AuthLogin;
import com.jcos.lc4e.core.util.annotation.AuthToken;
import com.jcos.lc4e.core.util.model.LoginSession;

public class AuthFilter implements HandlerInterceptor {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AuthFilter.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			// auth login
			AuthLogin login = ((HandlerMethod) handler).getMethodAnnotation(AuthLogin.class);
			if (login != null) {
				LoginSession loginSession = (LoginSession) request.getSession().getAttribute("loginSession");
				if (loginSession == null || loginSession.getUser() == null) {
					// for request from ajax
					response.setStatus(2000); // web errorCode in config
					if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
						return false;
					} else {
						String str = "<script language='javascript'>if (typeof jQuery == 'undefined') { window.location.href = 'ErrorPage/404'; }else{$.logout();}</script>";
						try {
							PrintWriter writer = response.getWriter();
							writer.write(str);
							writer.flush();
							writer.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return false;
					}
				}
			}
			// auth token for csrf simply
			AuthToken token = ((HandlerMethod) handler).getMethodAnnotation(AuthToken.class);
			if (token != null) {
				String urlLen = String.valueOf(request.getRequestURI().length() - 1);
				String lc4eToken = request.getHeader("lc4e-token");
				if (lc4eToken == null || lc4eToken.trim().equals("")) {
					response.setStatus(2001);
					return false;
				} else {
					String regex = "\\b" + urlLen + "(.*)" + urlLen + "\\b", unixTime = "";

					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(lc4eToken);

					while (m.find()) {
						unixTime = m.group(1);
					}
					if (unixTime == null || unixTime.trim().equals("")) {
						response.setStatus(2001);
						return false;
					}
					Long now = new Date().getTime();
					Long diff = now - Long.valueOf(unixTime);
					if (diff < 0) {
						response.setStatus(2001);
						return false;
					}
					Long day = diff / (1000 * 60 * 60 * 24);
					Long hour = (diff / (60 * 60 * 1000) - day * 24);
					Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
					Long second = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

					if (day > 0 || hour > 0 || min > 0 || second > 10) {
						response.setStatus(2001);
						return false;
					}
				}
			}

			boolean flag = true;
			return flag;
		} else {
			return true;
		}
	}
}
