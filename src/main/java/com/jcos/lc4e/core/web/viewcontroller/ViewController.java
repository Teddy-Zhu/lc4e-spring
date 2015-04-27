package com.jcos.lc4e.core.web.viewcontroller;

import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.service.UserService;
import com.jcos.lc4e.core.util.annotation.AuthToken;
import com.jcos.lc4e.core.util.l18n.ParserMessage;

@Controller
public class ViewController {

	@Inject
	private UserService userService;

	@Inject
	private ParserMessage msg;

	@Inject
	private LocaleResolver localeResolver;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "index";
	}

	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	public String signUp(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "SignUp";
	}

	@RequestMapping(value = "/Articles", method = RequestMethod.GET)
	public String articletest(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "articletest";
	}

	@RequestMapping(value = "/TopHots", method = RequestMethod.GET)
	public String tophostsTest(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "topHotTest";
	}

	@AuthToken
	@RequestMapping(value = "/TestShiro", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "topHotTest";
	}

	@RequestMapping(value = "/View", method = RequestMethod.GET)
	public String View(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) {
		model.addAttribute("exception", msg.L18N("test", locale));
		return "unauthorized";
	}

	@RequestMapping(value = "/ChangeLocale", method = RequestMethod.GET)
	@ResponseBody
	public Message ChangeLocale(HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) {
		localeResolver.setLocale(request, response, Locale.CHINESE);
		return new Message(true, "Change Success");
	}
}
