package com.teddy.lc4e.core.web.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.teddy.lc4e.core.database.model.UserBasicInfo;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.core.web.service.UserService;
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

import com.teddy.lc4e.core.database.model.User;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.core.util.annotation.ValidateField;
import com.teddy.lc4e.core.util.annotation.ValidateGroup;
import com.teddy.lc4e.core.util.credentials.PassDisposer;

@Controller
@RequestMapping(value = "/Member")
public class MemberController {

    @Autowired
    private UserService userService;
    @Autowired
    private PassDisposer passDisposer;
    @Autowired
    private ComVarDao comVarService;

    @RequestMapping(value = "/SignUp", method = RequestMethod.GET)
    @ResponseBody
    @RequiresGuest
    public Message SignUp( HttpServletRequest request, HttpServletResponse response, Model model,User user,UserBasicInfo basic) {
        if (userService.createUser(user,basic)) {
            return new Message(true, "Register Success");
        } else {
            return new Message("Register failed");
        }
    }

    @RequestMapping(value = "/SignIn", method = RequestMethod.GET)
    @ResponseBody
    @RequiresGuest
    @ValidateGroup(fields = {@ValidateField(index = 0, NotNull = true, minLen = 4, maxLen = 15), @ValidateField(index = 1, NotNull = true, minLen = 6)})
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
