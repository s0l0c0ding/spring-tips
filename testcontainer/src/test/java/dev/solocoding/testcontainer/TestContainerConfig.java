package dev.solocoding.testcontainer;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import org.junit.ClassRule;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestContainerConfig {

    @ClassRule
    private PostgreSQLContainer<?> postgre = new PostgreSQLContainer<>("postgres:9.6.18-alpine")
    .withInitScript("init-db.sql");

    @Bean
    public DataSource getDataSource(){
    postgre.start();

     var dataSource = new HikariDataSource();
     dataSource.setJdbcUrl(postgre.getJdbcUrl());
     dataSource.setUsername(postgre.getUsername());
     dataSource.setPassword(postgre.getPassword());
     return dataSource;
    }


}
