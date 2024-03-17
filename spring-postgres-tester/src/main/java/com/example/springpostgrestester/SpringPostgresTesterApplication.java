package com.example.springpostgrestester;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
public class SpringPostgresTesterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPostgresTesterApplication.class, args);
    }

    @RestController
    public class PingController {

        @Value("${spring.datasource.username}")
        private String username;

        @Value("${spring.datasource.password}")
        private String password;

        @Value("${spring.datasource.url}")
        private String url;


        @GetMapping("/ping")
        public Map<String, Object> hello() throws Exception {

            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setUrl(url);

            boolean execute = dataSource.getConnection().prepareStatement("SELECT 1").execute();

            return Map.of("postgres", execute);
        }
    }

}
