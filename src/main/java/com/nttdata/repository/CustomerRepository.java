package com.nttdata.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.nttdata.model.Customer;


public interface CustomerRepository extends ReactiveMongoRepository<Customer, String>{

}
