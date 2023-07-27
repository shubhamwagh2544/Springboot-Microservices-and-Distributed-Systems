package com.tigercodes.notification;

import com.tigercodes.amqp.RabbitMqMessageProducer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "com.tigercodes.amqp",
                "com.tigercodes.notification"
        }
)
@EnableEurekaClient
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    /*
        @Bean
        CommandLineRunner commandLineRunner(
                RabbitMqMessageProducer producer,
                NotificationConfig config
        ) {
            return args -> {
                producer.publish(
                        config.getInternalExchange(),
                        config.getInternalNotificationRoutingKey(),
                        //new Person("Shubham Wagh", 25)
                        "Hey Mr Shubham, Charge Your iPhone"
                );
            };
        }

        record Person(String name, Integer age) {
        }
     */
}
