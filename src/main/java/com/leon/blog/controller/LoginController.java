package com.leon.blog.controller;

import com.leon.blog.bean.db.User;
import com.leon.blog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月11日 23:50
 */
@RestController
@Slf4j
public class LoginController {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/{msgParam}", method = RequestMethod.POST)

    User home(@PathVariable("msgParam") String msg, @RequestBody User body) {
        String name = stringRedisTemplate.opsForValue().get("name");
        AbstractRoutingDataSource
        User user = userMapper.get(1L);
        System.out.println(body);
        System.out.println("name:" + name);
        System.out.println(user);
        return user;
    }
}
