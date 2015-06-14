package com.teddy.lc4e.core.web.controller.view;

import com.teddy.lc4e.core.database.service.InitDataBaseService;
import com.teddy.lc4e.core.database.service.MenuService;
import com.teddy.lc4e.core.database.service.UserService;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.core.util.annotation.*;
import com.teddy.lc4e.core.util.l18n.ParserMessage;
import com.teddy.lc4e.core.web.service.WebCacheManager;
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
    private MenuService menuService;
    @Autowired
    private ParserMessage msg;

    @Autowired
    private InitDataBaseService initDataBaseService;

    @Autowired
    private WebCacheManager webCacheManager;

    @Autowired
    private LocaleResolver localeResolver;

    @SetUIDataGroup(fields = {@SetUIDataField(functionName = "getMenuTree", attributeName = "menulist", key = "menus")})
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @SetComVar(comVar = {"SiteName"})
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
    @SetUIDataGroup(fields = {@SetUIDataField(functionName = "getMenuTree", attributeName = "Message", key = " ")})
    public String getMenus(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "System/Message";
    }


    @RequestMapping(value = "/ClearCache", method = RequestMethod.GET)
    @ValidateGroup(fields = {@ValidateField(index = 3, NotNull = true), @ValidateField(index = 4, NotNull = true)})
    @ResponseBody
    public Message getMenus2(HttpServletRequest request, HttpServletResponse response, Model model, String cacheName, String key) {
        if (webCacheManager.clearCacheByCacheNameAndKey(cacheName, key))
            return new Message(true, "clear success");
        else
            return new Message(false, "clear failed");
    }

    @RequestMapping(value = "/InitDB", method = RequestMethod.GET)
    @ResponseBody
    public Message initaldb(HttpServletRequest request, HttpServletResponse response, Model model) {
        if (initDataBaseService.initdb()) {
            return new Message(true, "success");
        }
        return new Message("failed");
    }

}
