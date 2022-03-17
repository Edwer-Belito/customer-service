package com.nttdata.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.BDDMockito.*;

import java.math.BigDecimal;
import java.util.Arrays;

import com.nttdata.model.Customer;
import com.nttdata.model.Product;
import com.nttdata.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
class CustomerServiceImplTest {
	
	@Mock
	private CustomerRepository customerRepository;
	
	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	
	@Test
	void createCustomer() {
		
		Customer customer2 = new Customer("2", "cliente 2", "personal 2",
				Arrays.asList(new Product()));
		Mono<Customer> monoCustomer = Mono.just(customer2);
		given(customerRepository.save(any())).willReturn(monoCustomer);
		
		Mono<Customer> result = customerServiceImpl.createCustomer(customer2);
		
		StepVerifier.create(result)
			.consumeNextWith(newCustomer -> {
				Assertions.assertNotNull(newCustomer);
				Assertions.assertEquals("2", newCustomer.getIdCustomer());
			})
			.verifyComplete();
	}
	
	@Test
	void findByCustomerId() {
		
		Customer customer2 = new Customer("2", "cliente 2", "personal 2",
				Arrays.asList(new Product()));
		Mono<Customer> monoCustomer = Mono.just(customer2);
		given(customerRepository.findById(anyString())).willReturn(monoCustomer);
		
		Mono<Customer> result = customerServiceImpl.findByCustomerId("2");
		
		StepVerifier.create(result)
			.consumeNextWith(dataFind -> {
				Assertions.assertEquals("cliente 2", dataFind.getName());
			})
			.verifyComplete();
	}
	
	@Test
	void findAllCustomer() {
		
		Customer customer2 = new Customer("2", "cliente 2", "personal 2",
				Arrays.asList(new Product()));
		Flux<Customer> fluxCustomer = Flux.just(customer2);
		
		given(customerRepository.findAll()).willReturn(fluxCustomer);
		
		Flux<Customer> result = customerServiceImpl.findAllCustomer();
		
		StepVerifier.create(result)
			.consumeNextWith(dataRetur -> {
				Assertions.assertEquals("personal 2", dataRetur.getCustomerType());
			})
			.verifyComplete();
	}
	
	@Test
	void updateSaldo() {
		
		Product product = new Product("001", "PASIVO", "PERSONAL", BigDecimal.TEN);
		Customer customer2 = new Customer("2", "cliente 2", "personal 2",
				Arrays.asList(product));
		Mono<Customer> monoCustomer = Mono.just(customer2);
		Mono<Product> monoProduc = Mono.just(product);
		
		given(customerRepository.findById(anyString())).willReturn(monoCustomer);
		
		given(customerRepository.save(any())).willReturn(monoCustomer);
		
		Mono<Customer> result = customerServiceImpl.updateSaldo("2", monoProduc);
		
		StepVerifier.create(result)
			.consumeNextWith(data -> {
				Assertions.assertEquals("2", data.getIdCustomer());
			})
			.verifyComplete();
	}

}
