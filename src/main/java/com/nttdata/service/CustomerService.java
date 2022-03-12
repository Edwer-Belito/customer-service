package com.nttdata.service;



import com.nttdata.model.Customer;
import com.nttdata.model.Product;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * CustomerService.
 */
public interface CustomerService {

/**
 * createCustomer.
 * @return Mono<Customer>
 */
Mono<Customer> createCustomer(Customer e);
/**
 * createCustomer.
 * @return String id
 * @param id
 */
Mono<Customer> findByCustomerId(String id);
/**
 * createCustomer.
 * @return Mono<Customer>
 */
Flux<Customer> findAllCustomer();
/**
 * createCustomer.
 * @return Mono<Customer>
 * @param Customer
 */
Mono<Customer> updateSaldo(String idCustomerOld, Mono<Product> product);
}
