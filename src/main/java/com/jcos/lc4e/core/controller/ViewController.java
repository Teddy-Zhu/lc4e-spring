package com.jcos.lc4e.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcos.lc4e.core.util.annotation.AuthLogin;

@Controller
public class ViewController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "index";
	}

	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	@AuthLogin
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
}
