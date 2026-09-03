package com.example.jdbctemplate.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    @Bean("mySQLDataSource")
    public DataSource mySQLDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
    @ConfigurationProperties(prefix = "spring.datasource.postgres")
    @Bean("postgresDataSource")
    public DataSource postgresDataSource() {
        return DataSourceBuilder
                .create()
                .build();
    }
}
