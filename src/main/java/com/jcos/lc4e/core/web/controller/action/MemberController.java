package com.jcos.lc4e.core.web.controller.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcos.lc4e.core.database.model.User;
import com.jcos.lc4e.core.database.service.UserService;
import com.jcos.lc4e.core.entity.Article;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.entity.Popup;
import com.jcos.lc4e.core.util.annotation.ValidateField;
import com.jcos.lc4e.core.util.annotation.ValidateGroup;
import com.jcos.lc4e.core.util.credentials.PassDisposer;
import com.jcos.lc4e.core.util.timeformat.RelativeDateFormat;

@Controller
@RequestMapping(value = "/Member")
public class MemberController {

	@Autowired
	private UserService userService;
	@Autowired
	private PassDisposer passDisposer;
	@Autowired
	private RelativeDateFormat dateFormat;

	@RequestMapping(value = "/SignUp", method = RequestMethod.GET)
	@ResponseBody
	@RequiresGuest
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
	@RequiresGuest
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

	@RequestMapping(value = "/GetArticles", method = RequestMethod.POST)
	@ResponseBody
	@ValidateGroup(fields = { @ValidateField(index = 0, NotNull = false, defaultInt = 1, minLen = 4, maxLen = 15), @ValidateField(index = 1, NotNull = false, defaultInt = 10, minLen = 6) })
	public List<Article> getTestArticle(Integer page, Integer size, HttpServletRequest request, HttpServletResponse response, Model model, Locale locale) {
		String[] cate = new String[] { "Java", "Obj-C", "C", "C++", "IOS", "Android" };
		String[] users = new String[] { "Admin", "Test", "Myas", "Liakx", "Google", "vsss" };
		Date now = new Date();
		List<Article> list = new ArrayList<Article>();
		for (int i = 0; i < size; i++) {
			list.add(new Article("/images/wireframe/image.png", new Popup("Matt", "Matt has been a member since July 2014"), "The friction between your thoughts and your code", cate[new Random().nextInt(5)], users[new Random().nextInt(5)], new Random().nextInt(100), dateFormat.format(
					randomDate("2015-05-11 13:00:00", now), locale, now), users[new Random().nextInt(5)]));
		}
		return list;
	}

	private static Date randomDate(String beginDate, Date endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = format.parse(beginDate);
			Date end = endDate;
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = format.parse(beginDate);
			Date end = format.parse(endDate);
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static long random(long begin, long end) {
		long rtnn = begin + (long) (Math.random() * (end - begin));
		if (rtnn == begin || rtnn == end) {
			return random(begin, end);
		}
		return rtnn;
	}

}
