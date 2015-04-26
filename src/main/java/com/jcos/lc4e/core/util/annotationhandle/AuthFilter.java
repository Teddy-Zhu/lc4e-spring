package com.jcos.lc4e.core.util.annotationhandle;

import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.util.annotation.AuthToken;

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
			// auth token for csrf simply
			AuthToken token = ((HandlerMethod) handler).getMethodAnnotation(AuthToken.class);
			if (token != null) {
				response.setStatus(200);
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				try {
					String urlLen = String.valueOf(request.getRequestURI().length() - 1);
					String lc4eToken = request.getHeader("lc4e-token");
					String ret = JSONObject.toJSONString(new Message("Token Auth Error"));
					if (lc4eToken == null || lc4eToken.trim().equals("")) {
						writer.write(ret);
						return false;
					} else {
						String regex = "\\d+", unixTime = "";
						Pattern p = Pattern.compile(regex);
						Matcher m = p.matcher(lc4eToken);

						while (m.find()) {
							unixTime = m.group();
						}
						if (!unixTime.equals(lc4eToken)) {
							writer.write(ret);
							return false;
						}
						regex = "\\b" + urlLen + "(.*)" + urlLen + "\\b";
						p = Pattern.compile(regex);
						m = p.matcher(lc4eToken);

						while (m.find()) {
							unixTime = m.group(1);
						}
						if (unixTime == null || "".equals(unixTime.trim())) {
							writer.write(ret);
							return false;
						}
						Long now = new Date().getTime();
						Long diff = now - Long.valueOf(unixTime);
						if (diff < 0) {
							writer.write(ret);
							return false;
						}
						Long day = diff / (1000 * 60 * 60 * 24);
						Long hour = (diff / (60 * 60 * 1000) - day * 24);
						Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
						Long second = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

						if (day > 0 || hour > 0 || min > 0 || second > 10) {
							writer.write(ret);
							return false;
						}
					}
				} finally {
					writer.flush();
					writer.close();
				}
			}
			return true;
		} else {
			return true;
		}
	}

	public static void main(String[] args) {
		String url = "914300135796369,914300135796469";
		String regex = "\\d+", unixTime = "";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(url);

		while (m.find()) {
			unixTime = m.group();
		}
		System.out.println(unixTime);
	}
}
