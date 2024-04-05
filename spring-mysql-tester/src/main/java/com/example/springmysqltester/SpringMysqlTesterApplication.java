package com.example.springmysqltester;

import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Map;

@SpringBootApplication
public class SpringMysqlTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMysqlTesterApplication.class, args);
	}

	@RestController
	public class PingController {

		@Autowired
		DataSource ds;



		@GetMapping("/ping")
		public Map<String, Object> hello() throws Exception {
			boolean execute = ds.getConnection().prepareStatement("SELECT 1").execute();
			return Map.of("mysql", execute);
		}
	}

}
