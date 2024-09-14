package com.prod.ecommerce.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.prod.ecommerce.user.controller.param.UserLoginParam;
import com.prod.ecommerce.user.model.User;
import com.prod.ecommerce.user.service.UserService;
import com.prod.ecommerce.util.AjaxResponse;
import com.prod.ecommerce.util.HttpResponseErrorCode;
import com.prod.ecommerce.exception.*;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;




@Slf4j
@RestController  /* It means 
                            a. The methods are for http request. 
                            b.  The http response are in Json format,
                    This is equal to add @Controller to class
                    and @ResponseBody to the front of each method.
                 */
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public AjaxResponse createUser(@RequestBody @Validated User user) {
        return userService.userRegister(user);
    }

    @GetMapping("/login")
    public AjaxResponse getUser(@RequestBody @Validated UserLoginParam userLoginParam) {
        // if (userLoginParam.userName.equals("")) {
        //     return AjaxResponse.error(HttpResponseErrorCode.badRequest,
        //      "User name cannot be empty.");
        // }
        // if (userLoginParam.password.equals("")) {
        //     return AjaxResponse.error(HttpResponseErrorCode.badRequest,
        //      "Password cannot be empty.");
        // }
        return userService.userLogin(
            userLoginParam.userName,
            userLoginParam.password);
    }

}
