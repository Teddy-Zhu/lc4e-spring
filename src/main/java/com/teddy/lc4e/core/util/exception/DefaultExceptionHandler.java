package com.teddy.lc4e.core.util.exception;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DefaultExceptionHandler {

	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(NativeWebRequest request, UnauthorizedException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getMessage());
		mv.setViewName("unauthorized");
		return mv;
	}

	@ExceptionHandler({ AuthorizationException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(NativeWebRequest request, AuthorizationException e) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}

	@ExceptionHandler({ IncorrectCredentialsException.class, UnknownAccountException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(NativeWebRequest request, Exception e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getMessage());
		mv.setViewName("unauthorized");
		return mv;
	}

	@ExceptionHandler({ ExcessiveAttemptsException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(NativeWebRequest request, ExcessiveAttemptsException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getMessage());
		mv.setViewName("unauthorized");
		return mv;
	}

	@ExceptionHandler({ LockedAccountException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(NativeWebRequest request, LockedAccountException e) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", e.getMessage());
		mv.setViewName("unauthorized");
		return mv;
	}
	
}
