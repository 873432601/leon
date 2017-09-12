package com.leon.blog;

import com.leon.blog.bean.db.User;
import com.leon.blog.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

	@Resource
	private UserMapper userMapper;
	@Test
	public void contextLoads() {
		User user = userMapper.get(1L);
		System.out.println(user);
	}

}
