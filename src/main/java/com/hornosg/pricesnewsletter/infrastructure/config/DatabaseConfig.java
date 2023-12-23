package com.hornosg.pricesnewsletter.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Value("${database.url}")
    private String dbUrl;

    @Value("${database.user}")
    private String user;

    @Value("${database.password}")
    private String password;

    @Bean
    public H2DbConnection dbConnection() throws SQLException {
        return new H2DbConnection(DriverManager.getConnection(dbUrl, user, password));
    }
}