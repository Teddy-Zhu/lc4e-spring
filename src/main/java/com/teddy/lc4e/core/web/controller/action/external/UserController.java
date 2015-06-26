package com.teddy.lc4e.core.web.controller.action.external;

import com.teddy.lc4e.core.database.model.User;
import com.teddy.lc4e.core.database.model.UserBasicInfo;
import com.teddy.lc4e.core.database.service.ComVarDao;
import com.teddy.lc4e.core.entity.webui.Message;
import com.teddy.lc4e.core.util.annotation.ValidateComVar;
import com.teddy.lc4e.core.util.annotation.ValidateComVarGroup;
import com.teddy.lc4e.core.util.annotation.ValidateField;
import com.teddy.lc4e.core.util.annotation.ValidateGroup;
import com.teddy.lc4e.core.util.common.Global;
import com.teddy.lc4e.core.util.credentials.PassDisposer;
import com.teddy.lc4e.core.web.service.UserService;
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
    @ValidateComVarGroup(fields = {@ValidateComVar(name = Global.REG, needBoolean = true)})
    @ValidateGroup(useSelect = true, validate = @ValidateComVar(name = Global.SREG, needBoolean = true),
            trueFields = {@ValidateField(index = 3, fieldName = "name", NotNull = true, minLen = 4, maxLen = 12), @ValidateField(index = 3, fieldName = "nick", NotNull = true, minLen = 3, maxLen = 10), @ValidateField(index = 3, fieldName = "password", NotNull = true, minLen = 6, maxLen = 14)},
            falseFields = {@ValidateField(index = 4, fieldName = "user.name", NotNull = true, minLen = 4, maxLen = 12), @ValidateField(index = 4, fieldName = "user.nick", NotNull = true, minLen = 3, maxLen = 10), @ValidateField(index = 4, fieldName = "user.password", NotNull = true, minLen = 6, maxLen = 14)})
    public Message SignUp(HttpServletRequest request, HttpServletResponse response, Model model, User user, UserBasicInfo basic) {
        if (userService.createUser(user, basic)) {
            return new Message(true, "Register Success");
        } else {
            return new Message("Register failed");
        }
    }
}