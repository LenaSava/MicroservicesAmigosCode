package com.olena.study.customer;

import com.olena.study.clients.fraud.FraudCheckResponse;
import com.olena.study.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;

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
;
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.ifFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        //todo: send notifications
    }
}
