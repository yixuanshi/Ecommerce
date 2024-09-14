package com.prod.ecommerce.user.service;

import java.util.List;

import com.prod.ecommerce.util.AjaxResponse;
import com.prod.ecommerce.user.model.User;

public interface UserService {
    public AjaxResponse userRegister(User user);
    public AjaxResponse userLogin(String userName, String password);
}
