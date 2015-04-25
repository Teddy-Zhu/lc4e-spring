package com.jcos.lc4e.core.web.viewcontroller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.service.UserService;
import com.jcos.lc4e.core.util.annotation.ValidateField;
import com.jcos.lc4e.core.util.annotation.ValidateGroup;

@Controller
public class ViewController {

	@Inject
	private UserService userService;

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

	@RequiresRoles("visitor")
	@RequestMapping(value = "/TestShiro", method = RequestMethod.GET)
	public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "topHotTest";
	}

	@RequestMapping(value = "/View", method = RequestMethod.GET)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = true, minLen = 4, maxLen = 15) })
	public Message SignIn(String username, HttpServletRequest request, HttpServletResponse response, Model model) {
		return new Message(true, userService.findByUsername(username).getStruserpass());

	}
}
