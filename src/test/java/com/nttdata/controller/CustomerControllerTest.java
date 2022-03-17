package com.nttdata.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.nttdata.model.Customer;
import com.nttdata.model.Product;
import com.nttdata.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
@WebFluxTest(value = CustomerController.class)
class CustomerControllerTest {

	@MockBean
	private CustomerService customerService;
	
	@Autowired
	private WebTestClient webTestClient;
	
	
	@Test
	void createCustomerTest() {
		
		Customer c = new Customer();
		when(customerService.createCustomer(any(Customer.class))).thenReturn(Mono.just(c));
		
		webTestClient.post().uri("/customer/create")
			.body(BodyInserters.fromValue(c))
			.accept(MediaType.APPLICATION_JSON)
			
			.exchange()
			.expectStatus()
			.isCreated();
		
		verify(customerService,times(1)).createCustomer((Customer)any());
		
	}
	
	@Test
	void getCustomerAll() {
		
		Customer customer1 = new Customer("1", "cliente 1", "personal 1",
				Arrays.asList(new Product()));
		Customer customer2 = new Customer("2", "cliente 2", "personal 2",
				Arrays.asList(new Product()));
		
		Flux<Customer> fluxCustomer = Flux.just(customer1,customer2);
		
		when(customerService.findAllCustomer()).thenReturn(fluxCustomer);
		
		Flux<Customer> dataMock = webTestClient.get().uri("/customer/getAll")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Customer.class)
				.getResponseBody();
		
		StepVerifier.create(dataMock)
			.expectSubscription()
			.expectNext(customer1)
			.expectComplete();
		
	}
	@Test
	void getCustomerId() {
		
		Mono<Customer> monoCustomer = Mono.just(new Customer("1", "cliente 1", "personal",
				Arrays.asList(new Product())));
		
		when(customerService.findByCustomerId(any())).thenReturn(monoCustomer);
		
		Flux<Customer> fluxCustomer = webTestClient.get().uri("/customer/get/1")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Customer.class)
				.getResponseBody();
		
		StepVerifier.create(fluxCustomer)
			.expectSubscription()
			.expectNextMatches(e -> e.getIdCustomer().equals("1"))
			.verifyComplete();
		
	}
	
	@Test
	void updateSaldo() {
		
		Product product1 = new Product("5", "PASIVO", "PERSONAL", BigDecimal.TEN);
		
		Customer customer1 = new Customer("1", "cliente 1", "personal 1",
				Arrays.asList(product1));
		
		given(customerService.updateSaldo(any(), any())).willReturn(Mono.just(customer1));
		
		Flux<Customer> fluxCustomerReturn = webTestClient.put().uri("/customer/updateSaldo/5")
				.exchange()
				.expectStatus().isOk()
				.returnResult(Customer.class)
				.getResponseBody();
		
		StepVerifier.create(fluxCustomerReturn)
			.expectSubscription()
			.expectNextMatches(data -> data.getIdCustomer().equals("1"))
			.expectComplete();
	}
	
	
}
