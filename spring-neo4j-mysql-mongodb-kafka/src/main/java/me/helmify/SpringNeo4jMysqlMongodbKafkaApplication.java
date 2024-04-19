package me.helmify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Map;

@SpringBootApplication
public class SpringNeo4jMysqlMongodbKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringNeo4jMysqlMongodbKafkaApplication.class, args);
	}

	@RestController
	public class MysqlPingController {
		@Autowired
		DataSource ds;



		@GetMapping("/ping/mysql")
		public Map<String, Object> hello() throws Exception {
			boolean execute = ds.getConnection().prepareStatement("SELECT 1").execute();
			return Map.of("mysql", execute);
		}
	}

}
