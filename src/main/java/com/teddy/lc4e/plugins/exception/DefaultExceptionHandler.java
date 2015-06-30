package com.teddy.lc4e.plugins.exception;

import com.teddy.lc4e.core.entity.webui.Message;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({UnauthorizedException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(NativeWebRequest request, UnauthorizedException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("redirect:exceptions/common");
        return mv;
    }

    @ExceptionHandler({AuthorizationException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(NativeWebRequest request, AuthorizationException e) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:exceptions/common");
        return mv;
    }

    @ExceptionHandler({IncorrectCredentialsException.class, UnknownAccountException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(NativeWebRequest request, Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("redirect:exceptions/common");
        return mv;
    }

    @ExceptionHandler({ExcessiveAttemptsException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(NativeWebRequest request, ExcessiveAttemptsException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("redirect:exceptions/common");
        return mv;
    }

    @ExceptionHandler({LockedAccountException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processException(NativeWebRequest request, LockedAccountException e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("exception", e.getMessage());
        mv.setViewName("redirect:exceptions/common");
        return mv;
    }

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Message processLc4eException(HttpServletRequest request, HttpServletResponse response, DuplicateKeyException e) {

        String message = e.getMessage(), error = "";
        if (message.indexOf("user.$name dup key") > 0) {
            error += "username is used\n";
        }
        if (message.indexOf("user.$nick dup key") > 0) {
            error += "nick is used\n";
        }
        return new Message(error);
    }
}
