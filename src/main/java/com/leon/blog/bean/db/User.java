package com.leon.blog.bean.db;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月12日 14:10
 */
@Data
public class User implements Serializable{
    private Long id;
    private String name;
    private Integer sex;
    private String interest;
}
