package me.helmify;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringPostgresRabbitRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPostgresRabbitRedisApplication.class, args);
    }

    @RestController
    public class PostgresPingController {

        @Value("${spring.datasource.username}")
        private String username;

        @Value("${spring.datasource.password}")
        private String password;

        @Value("${spring.datasource.url}")
        private String url;


        @GetMapping("/ping/postgres")
        public Map<String, Object> hello() throws Exception {

            PGSimpleDataSource dataSource = new PGSimpleDataSource();
            dataSource.setUser(username);
            dataSource.setPassword(password);
            dataSource.setUrl(url);

            boolean execute = dataSource.getConnection().prepareStatement("SELECT 1").execute();

            return Map.of("postgres", execute);
        }
    }

    @RestController
    public class RabbitmqPingController {

        @Autowired
        private RabbitTemplate template;
        @Autowired
        private Receiver receiver;

        @GetMapping("/ping/rabbitmq")
        public Map<String, Object> ping() throws Exception {
            String rabbitmq = "rabbitmq";
            template.convertAndSend(RabbitMqConfig.topicExchangeName, "foo.bar.baz", rabbitmq);
            String received = receiver.getMessages().poll(10, TimeUnit.SECONDS);
            return Map.of("rabbitmq", rabbitmq.equals(received));
        }
    }

    @RestController
    public class RedisPingController {

        @Autowired
        RedisRepo redisRepo;

        @GetMapping("/ping/redis")
        public Map<String, Object> ping() throws Exception {

            String id = UUID.randomUUID().toString();
            RedisEntity redisEntity = new RedisEntity(id, "1");

            RedisEntity save = redisRepo.save(redisEntity);

            return Map.of("redis", id.equals(save.getId()));
        }
    }


}
