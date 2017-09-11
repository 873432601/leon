package com.leon.blog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月11日 23:50
 */
@RestController
public class LoginController {

    @RequestMapping("/")
    String home(){
        return "Hello World！";
    }
}
