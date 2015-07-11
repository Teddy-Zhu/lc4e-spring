package com.teddy.lc4e.core.web.controller.action.external;

import com.teddy.lc4e.core.database.model.User;
import com.teddy.lc4e.core.database.model.UserBasicInfo;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.core.web.service.UserService;
import com.teddy.lc4e.global.Global;
import com.teddy.lc4e.plugins.annotation.ValidateComVar;
import com.teddy.lc4e.plugins.annotation.ValidateComVars;
import com.teddy.lc4e.plugins.annotation.ValidateParam;
import com.teddy.lc4e.plugins.annotation.ValidateParams;
import com.teddy.lc4e.plugins.shiro.credentials.PassDisposer;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by teddy on 2015/6/23.
 */
@Controller
public class UserController {


    @Autowired
    private UserService userService;
    @Autowired
    private PassDisposer passDisposer;
    @Autowired
    private ComVarDao comVarService;

    @RequestMapping(value = "/SignUp", method = RequestMethod.GET)
    @ResponseBody
    @RequiresGuest
    @ValidateComVars(fields = {@ValidateComVar(name = Global.REG, needValue = "true")})
    @ValidateParams(useSelect = true, validate = @ValidateComVar(name = Global.SREG, needValue = "true"),
            trueFields = {@ValidateParam(index = 3, fieldName = "name", required = true, minLen = 4, maxLen = 12), @ValidateParam(index = 3, fieldName = "nick", required = true, minLen = 3, maxLen = 10), @ValidateParam(index = 3, fieldName = "password", required = true, minLen = 6, maxLen = 14), @ValidateParam(index = 3, fieldName = "mail", required = true, regexStr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")},
            falseFields = {@ValidateParam(index = 4, fieldName = "user.name", required = true, minLen = 4, maxLen = 12), @ValidateParam(index = 4, fieldName = "user.nick", required = true, minLen = 3, maxLen = 10), @ValidateParam(index = 4, fieldName = "user.password", required = true, minLen = 6, maxLen = 14), @ValidateParam(index = 3, fieldName = "user.mail", required = true, regexStr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$")})
    public Message SignUp(HttpServletRequest request, HttpServletResponse response, Model model, User user, UserBasicInfo basic) {
        if (userService.createUser(user, basic)) {
            return new Message(true, "Register Success");
        } else {
            return new Message("Register failed");
        }
    }
}