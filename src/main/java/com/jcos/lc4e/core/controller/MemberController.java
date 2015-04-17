package com.jcos.lc4e.core.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcos.lc4e.core.util.annotation.ValidateField;
import com.jcos.lc4e.core.util.annotation.ValidateGroup;
import com.jcos.lc4e.core.util.model.Message;

@Controller
@RequestMapping(value = "/Member")
public class MemberController {

	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = true, minLen = 3) })
	public Message signUp(HttpServletRequest request, HttpServletResponse response, Model model) {
		return new Message("function end");
	}

}
