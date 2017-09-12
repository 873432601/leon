package com.leon.blog.mapper;

import com.leon.blog.bean.db.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月12日 14:11
 */
@Mapper
public interface UserMapper {
    User get(@Param("id") Long id);
}
