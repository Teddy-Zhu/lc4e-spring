package com.jcos.lc4e.core.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcos.lc4e.core.util.annotation.AuthLogin;
import com.jcos.lc4e.core.util.annotation.AuthToken;

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

}
