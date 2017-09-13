package com.leon.blog.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Description 主库配置
 * @Author 蔡学亮(xueliang.cai@mljr.com)
 * @Date 2017年09月12日 14:17
 */
@Configuration
@MapperScan(basePackages = {"com.leon.blog.mapper"},sqlSessionFactoryRef = "sqlSessionFactory")
@PropertySource("classpath:application.properties")
public class MyBatisConfig {
    private static final Logger logger = LoggerFactory.getLogger(MyBatisConfig.class);
    private static final String MAPPER_XML_LOCATION = "classpath*:mapper/**/*Mapper.xml";
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    //写库
    private static final String WRITE_DATASOURCE_PATH = "spring.datasource";


    @Bean
    @Primary
    @ConfigurationProperties(prefix = WRITE_DATASOURCE_PATH)
    public DataSource dataSource() throws Exception {
        return DataSourceBuilder.create().type(dataSourceType).build();
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
        System.out.println("------maxActive:"+druidDataSource.getTimeBetweenEvictionRunsMillis()+"-----------");
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
