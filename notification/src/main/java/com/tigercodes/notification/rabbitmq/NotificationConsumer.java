package com.tigercodes.notification.rabbitmq;

import com.tigercodes.clients.notification.NotificationRequest;
import com.tigercodes.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consume(NotificationRequest notificationRequest) {
        log.info("Consuming {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
        log.info("Consumed {} from queue", notificationRequest);
    }
}
