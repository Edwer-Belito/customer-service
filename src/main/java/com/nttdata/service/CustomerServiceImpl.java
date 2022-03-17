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

/**
 * CustomerServiceImpl.
 * @return @Service
 */
@Service

/**
 * CustomerServiceImpl.
 */
public class CustomerServiceImpl implements CustomerService {

/**
 * logger.
 */
private static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl
.class);

/**
 * CustomerServiceImpl.
 * @return @Autowired
 */
@Autowired
/**
 * CustomerRepository.
 */
private CustomerRepository customerRepository;

/**
 * CustomerServiceImpl.
 * @return @Autowired
 */
@Override

/**
 * createCustomer.
 * @param Customer
 * @return Mono<Customer>
 */
public final Mono<Customer> createCustomer(final Customer e) {
return customerRepository.save(e);
}
/**
 * CustomerServiceImpl.
 * @return @Autowired
 */
@Override
/**
 * createCustomer.
 * @param Customer
 * @return Mono<Customer>
 */
public final Mono<Customer> findByCustomerId(final String id) {
return customerRepository.findById(id);
}
/**
 * CustomerServiceImpl.
 * @return @Autowired
 */
@Override
/**
 * createCustomer.
 * @param Customer
 * @return Mono<Customer>
 */
public final Flux<Customer> findAllCustomer() {
return customerRepository.findAll();
}
/**
 * CustomerServiceImpl.
 * @return @Autowired
 */
@Override
/**
 * createCustomer.
 * @param Customer
 * @return Mono<Customer>
 */
public final Mono<Customer> updateSaldo(final String customerId, final Mono<Product> productChange) {

	logger.info("CustomerServiceImpl - updateSaldo -INICIO");

	return customerRepository.findById(customerId).flatMap(customerBD -> {

		Product pro = productChange.block();
		customerBD.getProducts().stream().filter(p -> p.getCode().equals(pro.getCode())).findFirst().map(pp -> {
			pp.setSaldo(pp.getSaldo().add(pro.getSaldo()));
			return pp;
		});

		return createCustomer(customerBD);
	}).switchIfEmpty(Mono.empty());

}
}
