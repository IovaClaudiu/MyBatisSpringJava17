package com.iova.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * This class represent the Persistence Configuration for the postgresql Database that is running in a docker container.
 * Uncomment the Configuration annotation if you want to use this configuration.
 */
@Configuration
@PropertySource("classpath:application.properties")
@MapperScan("com.iova.mybatis.mapper")
public class PersistencePostgresqlConfig {

    private final String POSTGRES_URL;
    private final String POSTGRES_USERNAME;
    private final String POSTGRES_PASSWORD;

    private static final String POSTGRES_DRIVER = "org.postgresql.Driver";

    public PersistencePostgresqlConfig(@Value("${com.iova.mybatis.postgres.url}") final String POSTGRES_URL,
                                       @Value("${com.iova.mybatis.postgres.username}") final String POSTGRES_USERNAME,
                                       @Value("${com.iova.mybatis.postgres.password}") final String POSTGRES_PASSWORD) {
        this.POSTGRES_URL = POSTGRES_URL;
        this.POSTGRES_USERNAME = POSTGRES_USERNAME;
        this.POSTGRES_PASSWORD = POSTGRES_PASSWORD;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(POSTGRES_DRIVER);
        dataSource.setUrl(POSTGRES_URL);
        dataSource.setUsername(POSTGRES_USERNAME);
        dataSource.setPassword(POSTGRES_PASSWORD);

        return dataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }
}
