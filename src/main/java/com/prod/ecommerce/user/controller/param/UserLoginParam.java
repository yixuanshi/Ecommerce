package com.prod.ecommerce.user.controller.param;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;

//@Data
public class UserLoginParam {
    @NotBlank(message = "username can't be empty --- validation")
    public String userName;
    @NotBlank(message = "password can't be empty --- validation")
    public String password;
}
