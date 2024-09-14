package com.prod.ecommerce.user.service;

import org.springframework.stereotype.Service;

import com.prod.ecommerce.util.AjaxResponse;
import com.prod.ecommerce.user.dao.EcommerceUserRepository;
import com.prod.ecommerce.user.model.User;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private EcommerceUserRepository userRepository;

    @Override
    public AjaxResponse userRegister(User user) {
        User saved_user = userRepository.save(user);
        log.info("registered user is: " + saved_user);
        return AjaxResponse.success();
    }

    @Override
    public AjaxResponse userLogin(String userName, String password) {
        User user = userRepository.findByName(userName);
        log.info("logged in user is: " + user);
        return AjaxResponse.success(user);
    }
}
