package com.nttdata.service;



import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Customer;
import com.nttdata.model.Product;
import com.nttdata.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Mono<Customer> createCustomer(Customer e) {
		return customerRepository.save(e);
	}

	@Override
	public Mono<Customer> findByCustomerId(String id) {
		return customerRepository.findById(id);
	}

	@Override
	public Flux<Customer> findAllCustomer() {
		return customerRepository.findAll();
	}

	@Override
	public Mono<Customer> updateSaldo(String idCustomerOld, Mono<Product> product) {

		
		 return customerRepository.findById(idCustomerOld)
	                .flatMap(customer1 -> {
	                	//customer1.idCustomer = idCustomerOld;
	                	
	                	
	                	customer1.product.saldo = 
	                			customer1.product.saldo.add(product.block().saldo);


	                    return createCustomer(customer1);
	                })
	                .switchIfEmpty(Mono.empty());
			
	}
}
