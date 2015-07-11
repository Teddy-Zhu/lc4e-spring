package com.teddy.lc4e.core.web.controller.view;

import com.teddy.lc4e.core.database.model.User;
import com.teddy.lc4e.core.database.service.dbtestDao;
import com.teddy.lc4e.core.entity.webui.Data;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.plugins.annotation.ValidateParam;
import com.teddy.lc4e.plugins.annotation.ValidateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UnknownFormatConversionException;

/**
 * Created by teddy on 2015/6/25.
 */
@Controller
@RequestMapping("/test")
public class testController {

    @Autowired
    private dbtestDao dbtestDao;

    @RequestMapping("/test")
    @ResponseBody
    public Message test1(String a) {
        dbtestDao.test(a);
        return new Message(a);
    }

    @RequestMapping("/test2")
    @ResponseBody
    public Message test2(String a) {
        dbtestDao.test2();
        return new Message(a);
    }

    @RequestMapping("/test3")
    @ResponseBody
    public Message test3(String a) {
        dbtestDao.test3(a);
        return new Message(a);
    }

    @RequestMapping("/test4")
    @ResponseBody
    public Message test4(String a) {
        dbtestDao.test4();
        return new Message(a);
    }

    @RequestMapping("/test5")
    @ResponseBody
    public Message test5(String a) {
        dbtestDao.test5(a);
        return new Message(a);
    }


    @RequestMapping("/test6")
    @ResponseBody
    @ValidateParams(fields = {@ValidateParam(index = 0, required = true, minLen = 1, maxLen = 15)})
    public Message test6(String a) {
        if (a.equals("0"))
            throw new UnknownFormatConversionException("aaaaa");
        return new Message(a);
    }


    @RequestMapping("/test7")
    @ResponseBody
    public Message test6(User a) {
        return new Message(false, new Data("a", a));
    }
}
