package com.leon.blog.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.leon.blog.mapper.BaseWriteMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月12日 14:17
 */
@Configuration
@MapperScan(basePackages = {"com.leon.blog.mapper"},sqlSessionFactoryRef = "sqlSessionFactory",markerInterface = BaseWriteMapper.class)
public class MyBatisConfig {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
    private static final String MAPPER_XML_LOCATION = "classpath*:mapper/**/*Mapper.xml";

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(DataSourceProperties properties) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("url", properties.getUrl());
        map.put("driverClassName", properties.getDriverClassName());
        map.put("username", properties.getUsername());
        map.put("password", properties.getPassword());
        map.put("initialSize", "1");
        map.put("maxActive", "20");
        map.put("maxWait", "60000");
        map.put("timeBetweenEvictionRunsMillis", "60000");
        map.put("validationQuery", "SELECT 'x'");
        map.put("testWhileIdle", "true");
        map.put("testOnBorrow", "false");
        map.put("testOnReturn", "false");
        map.put("poolPreparedStatements", "false");
        DataSource dataSource = DruidDataSourceFactory.createDataSource(map);
        return dataSource;
    }


    @Bean
    @Primary
    public DataSourceTransactionManager adsTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }


    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) {
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("------maxActive:"+druidDataSource.getMaxActive()+"-----------");
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        try {
            //添加XML目录
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATION));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
