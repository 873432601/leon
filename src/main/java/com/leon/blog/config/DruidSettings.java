package com.leon.blog.config;

import lombok.Data;
/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月13日 15:59
 */
@Data
public class DruidSettings {
    private String type;
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private Integer initialSize;
    private Integer minIdle;
    private Integer maxActive;
    private Long maxWait;
    private Long timeBetweenEvictionRunsMillis;
    private Long minEvictableIdleTimeMillis;
    private String validationQuery;
    private boolean testWhileIdle;
    private boolean testOnBorrow;
    private boolean testOnReturn;
    private boolean poolPreparedStatements;
    private Integer maxPoolPreparedStatementPerConnectionSize;
    private String filters;
    private String connectionProperties;
    private boolean useGlobalDataSourceStat;

}
