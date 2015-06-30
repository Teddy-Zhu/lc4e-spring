package com.teddy.lc4e.core.web.controller.view;

import com.teddy.lc4e.core.entity.webui.Article;
import com.teddy.lc4e.core.entity.webui.Data;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.core.entity.webui.Popup;
import com.teddy.lc4e.plugins.tools.RelativeDate;
import com.teddy.lc4e.plugins.tools.ParserMessage;
import com.teddy.lc4e.core.web.service.ComVariableData;
import com.teddy.lc4e.core.web.service.InitDBService;
import com.teddy.lc4e.core.web.service.WebCacheManager;
import com.teddy.lc4e.plugins.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ViewController {

    @Autowired
    private ParserMessage msg;

    @Autowired
    private InitDBService initDataBaseService;

    @Autowired
    private WebCacheManager webCacheManager;

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private ComVariableData comVariableData;

    @Autowired
    private RelativeDate dateFormat;

    @ValidateGroup(fields = {@ValidateField(index = 3, defaultInt = 1)})
    @SetUIDataGroup(fields = {@SetUIDataField(functionName = "getMenuTree", attributeName = "menulist", key = "menus")})
    @RequestMapping(value = {"/Page/{page}"}, method = RequestMethod.GET)
    @ResponseBody
    public Message home(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable("page") Integer page, Locale locale) {
        Integer size = (Integer) comVariableData.getComVarByName("IndexPageSize");
        String[] cate = new String[]{"Java", "Obj-C", "C", "C++", "IOS", "Android"};
        String[] users = new String[]{"Admin", "Test", "Myas", "Liakx", "Google", "vsss"};
        Date now = new Date();
        List<Article> list = new ArrayList<Article>();
        for (int i = 0; i < size; i++) {
            list.add(new Article("/images/wireframe/image.png", new Popup("Matt", "Matt has been a member since July 2014"), "The friction between your thoughts and your code", cate[new Random().nextInt(cate.length - 1)], users[new Random().nextInt(users.length - 1)], new Random().nextInt(100),
                    dateFormat.format(randomDate("2015-05-11 13:00:00", now), locale, now), users[new Random().nextInt(users.length - 1)]));
        }
        return new Message(true, new Data("list", list));
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    @SetUIDataGroup(fields = {@SetUIDataField(functionName = "getMenuTree", attributeName = "menulist", key = "menus")})
    @SetComVar(comVar = {"SiteName"})
    public String homeIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("page", 1);
        return "index";
    }

    @RequestMapping(value = "/articleTemplate", method = RequestMethod.GET)
    public String articleTemplate(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "template/articleIndex";
    }

    @RequestMapping(value = "/Articles", method = RequestMethod.GET)
    public String articletest(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "articletest";
    }

    @RequestMapping(value = "/Exception", method = RequestMethod.GET)
    public String exception(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "exceptions/common";
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
    public Message ChangeLocale(HttpServletRequest request, HttpServletResponse response, Model model) {
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
