package com.back.service.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动项目
 * @author magicHat
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class },scanBasePackages = "com.back.service.*")
public class BackServiceApplication {

    public static void main(String[] args){
        SpringApplication.run(BackServiceApplication.class);
    }
}
