package com.leon.blog.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
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
@PropertySource("classpath:application.properties")
@MapperScan(basePackages = {"com.leon.blog.mapper"})
@Configuration
@Slf4j
public class MyBatisConfig {

    private static final String MAPPER_XML_LOCATION = "classpath*:mapper/**/*Mapper.xml";
    //数据bean路径
    private static final String DB_BEAN_LOCATION = "com.leon.blog.bean.db";
    //数据库连接池类型
    @Value("${spring.datasource.type}")
    private static  Class<? extends DataSource> dataSourceType;
    //读库(主库)
    private static final String READ_DATASOURCE_PREFIX = "spring.datasource";
    //写库(从库)
    private static final String WRITE_DATASOURCE_PREFIX = "spring.slave";


/*****************************************************读库*****************************************************/
    @Bean(name = "readDataSource")
    @ConfigurationProperties(prefix = READ_DATASOURCE_PREFIX)
    public DataSource readDataSource() throws Exception {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name="readSqlSessionFactory")
    public SqlSessionFactory readSqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) {
        return createSqlSessionFactory(dataSource);
    }


/*****************************************************写库*****************************************************/
    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = WRITE_DATASOURCE_PREFIX)
    public DataSource writeDataSource() throws Exception {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "writeTransactionManager")
    @Primary
    public DataSourceTransactionManager writeTransactionManager(@Qualifier("writeDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "writeqlSessionFactory")
    @Primary
    public SqlSessionFactory writeqlSessionFactory(@Qualifier("writeDataSource") DataSource dataSource) {
        return createSqlSessionFactory(dataSource);
    }

    /**
     * 创建SqlSessionFactory私有方法
     * @param dataSource
     * @return
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource){

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置数据库bean路径，在mapper.xml文件中的parameterType和resultType不用写完全限定类名
        sqlSessionFactoryBean.setTypeAliasesPackage(DB_BEAN_LOCATION);
        sqlSessionFactoryBean.setDataSource(dataSource);
        try {
            //添加mapper.xml目录
            sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_XML_LOCATION));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
