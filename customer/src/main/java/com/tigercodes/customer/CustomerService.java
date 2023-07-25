package com.tigercodes.customer;

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

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        "Welcome to Tigercodes"
                )
        );
    }
}
