package com.prod.ecommerce.util;

import com.prod.ecommerce.exception.CustomException;
import lombok.Data;

@Data
public class AjaxResponse {
    private boolean isok;
    private int code;
    private String message;
    private Object data;

    public AjaxResponse() {}

    public static AjaxResponse success() {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("Request ok.");
        return ajaxResponse;
    }

    public static AjaxResponse success(Object obj) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(true);
        ajaxResponse.setCode(200);
        ajaxResponse.setMessage("Request ok.");
        ajaxResponse.setData(obj);
        return ajaxResponse;
    }

    public static AjaxResponse error(CustomException e) {
        AjaxResponse ajaxResponse = new AjaxResponse();
        ajaxResponse.setIsok(false);
        ajaxResponse.setCode(e.getCode());
        ajaxResponse.setMessage(e.getMessage());
        return ajaxResponse;
    }
}
