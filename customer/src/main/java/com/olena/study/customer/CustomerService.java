package com.olena.study.customer;

import com.olena.study.clients.fraud.FraudCheckResponse;
import com.olena.study.clients.fraud.FraudClient;
import com.olena.study.clients.notification.NotificationClient;
import com.olena.study.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if emails valid
        //todo: check if email not taken
        //todo: check ifFraudster
        //todo: store customer in db
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.ifFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        //todo: send notifications
        notificationClient.sendNotification(new NotificationRequest(customer.getId(), customer.getEmail(),
                String.format("Hi, %s, welcome to study", customer.getFirstName())));

    }
}
