package com.nttdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Customer;
import com.nttdata.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public void createCustomer(Customer e) {
		customerRepository.save(e).subscribe();
	}

	@Override
	public Mono<Customer> findByCustomerId(Integer id) {
		return customerRepository.findById(id);
	}

	@Override
	public Flux<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

}
