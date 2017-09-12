package com.leon.blog.bean.db;

import lombok.Data;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月12日 14:10
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer sex;
    private String interest;
}
