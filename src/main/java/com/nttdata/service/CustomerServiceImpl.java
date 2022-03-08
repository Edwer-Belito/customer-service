package com.nttdata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.model.Customer;
import com.nttdata.model.Product;
import com.nttdata.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

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
	public Mono<Customer> updateSaldo(String customerId, Mono<Product> productChange) {

		logger.info("CustomerServiceImpl - updateSaldo -INICIO");

		return customerRepository.findById(customerId).flatMap(customerBD -> {

			Product pro = productChange.block();
			customerBD.getProducts().stream().filter(p -> p.getCode().equals(pro.getCode())).findAny().map(pp -> {
				pp.setSaldo(pp.getSaldo().add(pro.getSaldo()));
				return pp;
			});

			return createCustomer(customerBD);
		}).switchIfEmpty(Mono.empty());

	}
}
