package com.jcos.lc4e.core.util.annotationhandle;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.alibaba.fastjson.JSONObject;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.util.annotation.ValidateField;
import com.jcos.lc4e.core.util.annotation.ValidateGroup;
import com.jcos.lc4e.core.util.annotation.ValidateToken;

@Component
@Aspect
public class ValidateAspectHandle {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ValidateAspectHandle.class);

	@SuppressWarnings("finally")
	@Around("@annotation(com.jcos.lc4e.core.util.annotation.ValidateGroup)")
	public Object validateAroundParameter(ProceedingJoinPoint joinPoint) throws Throwable {
		boolean flag = false;
		ValidateGroup an = null;
		Object[] args = null;
		Method method = null;
		Object target = null;
		String methodName = null;
		try {
			methodName = joinPoint.getSignature().getName();
			target = joinPoint.getTarget();
			method = getMethodByClassAndName(target.getClass(), methodName);
			args = joinPoint.getArgs(); // all parameters
			an = (ValidateGroup) getAnnotationByMethod(method, ValidateGroup.class);
			flag = validateField(an.fields(), args);
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				return joinPoint.proceed();
			} else {
				return new Message("Parameter validation error");
			}
		}
	}

	@SuppressWarnings("finally")
	@Around("@annotation(com.jcos.lc4e.core.util.annotation.ValidateToken)")
	public Object validateAroundToken(ProceedingJoinPoint joinPoint) throws Throwable {
		boolean flag = false;
		ValidateToken an = null;
		Object[] args = null;
		Method method = null;
		Object target = null;
		String methodName = null;
		try {
			methodName = joinPoint.getSignature().getName();
			target = joinPoint.getTarget();
			method = getMethodByClassAndName(target.getClass(), methodName);
			args = joinPoint.getArgs(); // all parameters
			an = (ValidateToken) getAnnotationByMethod(method, ValidateToken.class);
			flag = validateToken(an, args);
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				return joinPoint.proceed();
			} else {
				if (method.getReturnType().getName().equals("java.lang.String")) {
					Model model = (Model) args[an.modIndex()];
					model.addAttribute("Message", JSONObject.toJSONString(new Message("Token Auth Error")));
					return "System/Message";
				} else {
					return new Message("Token Auth Error");
				}
			}
		}
	}

	public boolean validateToken(ValidateToken vt, Object[] args) {

		HttpServletRequest request = (HttpServletRequest) args[vt.reqIndex()];

		String urlLen = String.valueOf(request.getRequestURI().length() - 1);
		String lc4eToken = request.getHeader("lc4e-token");

		if (lc4eToken == null || lc4eToken.trim().equals("")) {
			return false;
		} else {
			String regex = "\\d+", unixTime = "";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(lc4eToken);

			while (m.find()) {
				unixTime = m.group();
			}
			if (!unixTime.equals(lc4eToken)) {

				return false;
			}
			regex = "\\b" + urlLen + "(.*)" + urlLen + "\\b";
			p = Pattern.compile(regex);
			m = p.matcher(lc4eToken);

			while (m.find()) {
				unixTime = m.group(1);
			}
			if (unixTime == null || "".equals(unixTime.trim())) {
				return false;
			}
			Long now = new Date().getTime();
			Long diff = now - Long.valueOf(unixTime);
			if (diff < 0) {
				return false;
			}
			Long day = diff / (1000 * 60 * 60 * 24);
			Long hour = (diff / (60 * 60 * 1000) - day * 24);
			Long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
			Long second = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

			if (day > 0 || hour > 0 || min > 0 || second > 10) {
				return false;
			}
		}
		return true;
	}

	/**
	 * handle parameters
	 * 
	 * @param valiedatefiles
	 * @param args
	 * @return
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public boolean validateField(ValidateField[] valiedatefiles, Object[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		for (ValidateField validateField : valiedatefiles) {
			Object arg = null;
			if ("".equals(validateField.fieldName())) {
				arg = args[validateField.index()];
			} else {
				arg = getFieldByObjectAndFileName(args[validateField.index()], validateField.fieldName());
			}

			if (validateField.defaultInt() != -1 || !"".equals(validateField.defaultString())) {
				arg = validateField.defaultInt() == -1 ? validateField.defaultString() : validateField.defaultInt();
			}

			if (validateField.NotNull()) {
				if (arg == null) {
					return false;
				}
			} else { // if parameter is null, should be return immediately
				if (arg == null)
					return true;
			}

			if (arg instanceof String) {
				if (validateField.maxLen() > 0) {
					if (((String) arg).length() > validateField.maxLen())
						return false;
				}

				if (validateField.minLen() > 0) {
					if (((String) arg).length() < validateField.minLen())
						return false;
				}
			}
			if (arg instanceof Integer) {
				if (validateField.maxVal() != -1) {
					if ((Integer) arg > validateField.maxVal())
						return false;
				}

				if (validateField.minVal() != -1) {
					if ((Integer) arg < validateField.minVal())
						return false;
				}
			}
			if (!"".equals(validateField.regStr())) {
				if (arg instanceof String) {
					if (!((String) arg).matches(validateField.regStr()))
						return false;
				} else {
					return false;
				}
			}
		}
		return true;
	}

	public Object getFieldByObjectAndFileName(Object targetObj, String fileName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		String tmp[] = fileName.split("\\.");
		Object arg = targetObj;
		for (int i = 0; i < tmp.length; i++) {
			Method methdo = arg.getClass().getMethod(getGetterNameByFieldName(tmp[i]));
			arg = methdo.invoke(arg);
		}
		return arg;
	}

	/**
	 * get field get function
	 */
	public String getGetterNameByFieldName(String fieldName) {
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * according annotation class to get annotation
	 */
	public Annotation getAnnotationByMethod(Method method, Class annoClass) {
		Annotation all[] = method.getAnnotations();
		for (Annotation annotation : all) {
			if (annotation.annotationType() == annoClass) {
				return annotation;
			}
		}
		return null;
	}

	/**
	 * according method and class to get method
	 */
	public Method getMethodByClassAndName(Class c, String methodName) {
		Method[] methods = c.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}
		return null;
	}
}
