package com.tigercodes.customer;

import com.tigercodes.amqp.RabbitMqMessageProducer;
import com.tigercodes.clients.fraud.FraudCheckResponse;
import com.tigercodes.clients.fraud.FraudClient;
import com.tigercodes.clients.notification.NotificationClient;
import com.tigercodes.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;
    private final RabbitMqMessageProducer rabbitMqMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email is valid
        //todo: check if email is not taken

        customerRepository.saveAndFlush(customer);

        //todo: check if customer is fraudster

        /*
            FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                    "http://FRAUD/api/v1/fraud-check/{customerId}",
                    FraudCheckResponse.class,
                    customer.getId()
            );
         */

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        //todo: send notification

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hello %s, Welcome to Tigercodes..", customer.getFirstName())
        );

        //notificationClient.sendNotification(notificationRequest);

        rabbitMqMessageProducer.publish(
                "internal.exchange",
                "internal.notification.routing-key",
                notificationRequest
        );

    }
}
