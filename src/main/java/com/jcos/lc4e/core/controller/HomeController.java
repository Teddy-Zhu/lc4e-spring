package com.jcos.lc4e.core.controller;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcos.lc4e.core.util.annotation.ValidateField;
import com.jcos.lc4e.core.util.annotation.ValidateGroup;
import com.jcos.lc4e.core.util.model.Message;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "index";
	}

	@RequestMapping(value = "/testValite", method = RequestMethod.GET)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = true, minLen = 3) })
	public Message home2(String test, Model model) {
		return new Message("function end");
	}
}
