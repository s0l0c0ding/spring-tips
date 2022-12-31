package dev.solocoding.jsonhibernate;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.zaxxer.hikari.HikariDataSource;

@Testcontainers
public class TestContainerConfig {
    
    @Container
	public static MySQLContainer<?> mysql = new MySQLContainer<>(DockerImageName.parse("mysql"));

    @Bean
    public DataSource getDataSource(){
        mysql.start();
        var dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(mysql.getJdbcUrl());
        dataSource.setUsername(mysql.getUsername());
        dataSource.setPassword(mysql.getPassword());
        return dataSource;
    }

}
