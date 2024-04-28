package me.helmify;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.Statement;
import com.datastax.oss.driver.internal.core.cql.DefaultSimpleStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
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

		@Autowired
		ElasticsearchOperations es;

		@GetMapping("/ping/elasticsearch")
		public Map<String, Object> ping()   {

			ElasticsearchEntity elasticsearchEntity = new ElasticsearchEntity();
			String test = "test";
			elasticsearchEntity.setName(test);
			ElasticsearchEntity saved = es.save(elasticsearchEntity);
			String id = saved.getId();

			return Map.of("elasticsearch", test.equals(es.get(id, ElasticsearchEntity.class).getName()));
		}
	}

	@RestController
	public class CouchbasePingController {

		@Autowired
		CouchbaseTemplate cb;

		@GetMapping("/ping/couchbase")
		public Map<String, Object> ping() {
			CouchbaseEntity couchbaseEntity = new CouchbaseEntity();
			String test = "test";
			couchbaseEntity.setName(test);
			couchbaseEntity.setId(test);
			CouchbaseEntity saved = cb.save(couchbaseEntity);
			String id = saved.getId();

			return Map.of("couchbase", test.equals(cb.findById(CouchbaseEntity.class).one(id).getName()));
		}
	}

	@RestController
	public class CassandraPingController {

		@Autowired
		CassandraTemplate ct;

		@GetMapping("/ping/cassandra")
		public Map<String, Object> ping() {

			SimpleStatement simpleStatement = SimpleStatement.newInstance("CREATE TABLE IF NOT EXISTS cassandraentity (id text PRIMARY KEY, name text)");
			ct.execute(simpleStatement);

			String id = "1";
			String name = "test";
			CassandraEntity cassandraEntity = new CassandraEntity(id, name);
			ct.insert(cassandraEntity);

			return Map.of("cassandra", name.equals(ct.query(CassandraEntity.class).one().orElseThrow().getName()));
		}
	}
}
