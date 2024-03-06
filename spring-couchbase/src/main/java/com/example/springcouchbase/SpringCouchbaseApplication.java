package com.example.springcouchbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableCouchbaseRepositories(basePackages = "com.example.springcouchbase")
@SpringBootApplication
public class SpringCouchbaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCouchbaseApplication.class, args);
    }

}
