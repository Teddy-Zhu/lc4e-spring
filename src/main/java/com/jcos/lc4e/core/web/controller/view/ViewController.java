package com.jcos.lc4e.core.web.controller.view;

import com.jcos.lc4e.core.database.dao.UserMapper;
import com.jcos.lc4e.core.database.service.UserService;
import com.jcos.lc4e.core.entity.Message;
import com.jcos.lc4e.core.util.annotation.UIDataField;
import com.jcos.lc4e.core.util.annotation.UIDataGroup;
import com.jcos.lc4e.core.util.annotation.ValidateToken;
import com.jcos.lc4e.core.util.l18n.ParserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
public class ViewController {

    @Autowired
    private UserService userService;


    @Autowired
    private ParserMessage msg;


    @Autowired
    private LocaleResolver localeResolver;

    @UIDataGroup(fields = {@UIDataField(functionName = "getMenuTree", attributeName = "menulist", key = "menus")})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "index";
    }

    @RequestMapping(value = "/articleTemplate", method = RequestMethod.GET)
    public String articleTemplate(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "template/articleIndex";
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

    @ValidateToken
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
        localeResolver.setLocale(request, response, Locale.CHINA);
        return new Message(true, "Change Success");
    }


    @RequestMapping(value = "/GetMenus", method = RequestMethod.GET)
    @UIDataGroup(fields = {@UIDataField(functionName = "getMenuTree", attributeName = "Message", key = "menus")})
    public String getMenus(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "System/Message";
    }

}
