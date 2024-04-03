package com.example.springrabbitmqtester;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpringRabbitmqTesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitmqTesterApplication.class, args);
	}

	@RestController
	public class PingController {

		private final RabbitTemplate template;
		private final Receiver receiver;

		public PingController(RabbitTemplate template, Receiver receiver) {
			this.template = template;
			this.receiver = receiver;
		}

		@GetMapping("/ping")
		public Map<String, Object> ping() throws Exception {
			String rabbitmq = "rabbitmq";
			template.convertAndSend(RabbitMqConfig.topicExchangeName, "foo.bar.baz", rabbitmq);
			String received = receiver.getMessages().poll(10, TimeUnit.SECONDS);
			return Map.of("rabbitmq", rabbitmq.equals(received));
		}
	}
}
