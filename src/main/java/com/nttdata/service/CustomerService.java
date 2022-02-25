package com.nttdata.service;


import com.nttdata.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    void createCustomer(Customer e);
    Mono<Customer> findByCustomerId(Integer id);
    Flux<Customer> findAllCustomer ();
}
