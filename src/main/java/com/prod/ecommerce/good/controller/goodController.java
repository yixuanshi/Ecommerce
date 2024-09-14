package com.prod.ecommerce.good.controller;

import org.springframework.web.bind.annotation.RestController;

import com.prod.ecommerce.good.model.Good;
import com.prod.ecommerce.good.service.GoodService;
import com.prod.ecommerce.util.AjaxResponse;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("/good")
public class goodController {


}
