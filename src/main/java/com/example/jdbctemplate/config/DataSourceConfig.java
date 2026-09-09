package com.example.jdbctemplate.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Bean("mySQLDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.mysql.jdbc")
    public HikariDataSource mySQLDataSource() {

            System.out.println("in the datasource");

            HikariDataSource dataSource = DataSourceBuilder
                    .create()
                    .type( HikariDataSource.class)
                    .build();

            System.out.println("===== MYSQL DATASOURCE =====");
            System.out.println("URL      = " + dataSource.getJdbcUrl());
        System.out.println("Username = " + dataSource.getUsername());
        System.out.println("Driver   = " + dataSource.getDriverClassName());
        System.out.println("Pool     = " + dataSource.getPoolName());
            return dataSource;
        }
    

    // @ConfigurationProperties(prefix = "spring.datasource.postgres.jdbc")
    // @Bean("postgresDataSource")
    // public DataSource postgresDataSource() {
    //     return DataSourceBuilder
    //             .create()
    //             .type(HikariDataSource.class)
    //             .build();
    // }
    
    @Primary 
    @Bean("mySQLJdbcTemplate")
    public JdbcTemplate mySQLJdbcTemplate(@Qualifier("mySQLDataSource") DataSource dataSource) throws SQLException {
        System.out.println("===== MYSQL DATASOURCE =====");
        System.out.println("URL      = " + dataSource.getConnection().getMetaData().getURL());
        // System.out.println("Username = " + dataSource.getUsername());
        // System.out.println("Driver   = " + dataSource.getDriverClassName());
        // System.out.println("Pool     = " + dataSource.getPoolName());
        return new JdbcTemplate(dataSource);
    }

    // @Bean("postgresJdbcTemplate")
    // public JdbcTemplate postgresJdbcTemplate(@Qualifier("postgresDataSource") DataSource datasource){
    //     return new JdbcTemplate(datasource);
    // }
    @Primary
    @Bean("mySQLNamedJdbcTemplate")
    public NamedParameterJdbcTemplate mySQLNamedJdbcTemplate( @Qualifier("mySQLDataSource") HikariDataSource dataSource) {

        System.out.println("named jdbc template");
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
