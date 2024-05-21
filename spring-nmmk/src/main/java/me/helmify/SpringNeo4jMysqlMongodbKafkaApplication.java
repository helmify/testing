package me.helmify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static me.helmify.KafkaTopicConfig.KAKFA_TOPIC;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableMongoRepositories
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

	@RestController
	public class Neo4jPingController {

		@Autowired
		NeoRepository repo;

		@GetMapping("/ping/neo4j")
		public Map<String, Object> hello() throws Exception {
			NeoEntity entity = new NeoEntity();
			String name = "test";
			entity.setName(name);
			repo.save(entity);
			return Map.of("neo4j", name.equals(repo.findAll().iterator().next().getName()));
		}
	}

	@RestController
	public class MongoDbPingController {

		@Autowired
		MongoRepository repo;

		@GetMapping("/ping/mongodb")
		public Map<String, Object> hello() throws Exception {
			MongoEntity entity = new MongoEntity();
			String name = "test";
			entity.setName(name);
			repo.save(entity);
			return Map.of("mongodb", name.equals(repo.findAll().iterator().next().getName()));
		}
	}

	@RestController
	public class KafkaPingController {

		@Autowired
		private KafkaTemplate<String, String> kafkaTemplate;

		@GetMapping("/ping/kafka")
		public Map<String, Object> hello() throws Exception {
			String test = "kafka";
			kafkaTemplate.send(KAKFA_TOPIC, test);
			String poll = KafkaConsumer.messages.poll(10, TimeUnit.SECONDS);
			return Map.of("kafka", test.equals(poll));
		}
	}
}
