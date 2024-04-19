package me.helmify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.Map;

@SpringBootApplication
public class SpringMariadbElasticCouchbaseCassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMariadbElasticCouchbaseCassandraApplication.class, args);
	}
	@RestController
	public class MariadbPingController {
		@Autowired
		DataSource ds;

		@GetMapping("/ping/mariadb")
		public Map<String, Object> hello() throws Exception {
			boolean execute = ds.getConnection().prepareStatement("SELECT 1").execute();
			return Map.of("mariadb", execute);
		}
	}

	@RestController
	public class ElasticsearchPingController {

		@GetMapping("/ping/elasticsearch")
		public Map<String, Object> hello() throws Exception {
			return Map.of("elasticsearch", false);
		}
	}

	@RestController
	public class CouchbasePingController {

		@GetMapping("/ping/couchbase")
		public Map<String, Object> hello() throws Exception {
			return Map.of("couchbase", false);
		}
	}

	@RestController
	public class CassandraPingController {

		@GetMapping("/ping/cassandra")
		public Map<String, Object> hello() throws Exception {
			return Map.of("cassandra", false);
		}
	}
}
