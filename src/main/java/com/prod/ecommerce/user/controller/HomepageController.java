package com.prod.ecommerce.user.controller;

import org.springframework.web.bind.annotation.RestController;

import com.prod.ecommerce.user.service.UserService;
import com.prod.ecommerce.util.AjaxResponse;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;




@Slf4j
@RestController
public class HomepageController {

    @Resource
    UserService userService;

    @GetMapping("/")
    public AjaxResponse getHomepage() {
        return AjaxResponse.success("Welcome to ecommerce application homepage!");
    }

}
