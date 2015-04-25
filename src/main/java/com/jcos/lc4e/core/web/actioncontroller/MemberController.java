package com.jcos.lc4e.core.web.actioncontroller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcos.lc4e.core.database.model.User;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.service.UserService;
import com.jcos.lc4e.core.util.annotation.ValidateField;
import com.jcos.lc4e.core.util.annotation.ValidateGroup;
import com.jcos.lc4e.core.util.credentials.PassDisposer;

@Controller
@RequestMapping(value = "/Member")
public class MemberController {

	@Inject
	private UserService userService;
	@Inject
	private PassDisposer passDisposer;

	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = true, minLen = 4, maxLen = 15), @ValidateField(index = 1, NotNull = true, minLen = 6) })
	public Message signUp(String username, String password, HttpServletRequest request, HttpServletResponse response, Model model) {
		User user = new User(username, password);
		passDisposer.encryptPassword(user);
		if (userService.createUser(user)) {
			return new Message(true, "Register Success");
		} else {
			return new Message("Register failed");
		}
	}

	@RequestMapping(value = "/SignIn", method = RequestMethod.GET)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = true, minLen = 4, maxLen = 15), @ValidateField(index = 1, NotNull = true, minLen = 6) })
	public Message SignIn(String username, String password, HttpServletRequest request, HttpServletResponse response, Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		subject.login(token);
		if (subject.isAuthenticated()) {
			return new Message(true, "Login Success");
		} else {
			return new Message("Login failed");
		}
	}

}
