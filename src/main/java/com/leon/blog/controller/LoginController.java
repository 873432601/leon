package com.leon.blog.controller;

import com.leon.blog.bean.db.User;
import com.leon.blog.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月11日 23:50
 */
@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/")
    String home(){
        String name = stringRedisTemplate.opsForValue().get("name");
        User user = userMapper.get(1L);
        System.out.println("name:"+name);
        System.out.println(user);
        return "Hello World！";
    }
}
