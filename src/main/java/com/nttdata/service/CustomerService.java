package com.nttdata.service;



import com.nttdata.model.Customer;
import com.nttdata.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	Mono<Customer> createCustomer(Customer e);
    Mono<Customer> findByCustomerId(String id);
    Flux<Customer> findAllCustomer ();
    
    Mono<Customer> updateSaldo(String idCustomerOld, Mono<Product> product);
}
