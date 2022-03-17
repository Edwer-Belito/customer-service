package com.nttdata.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.azure.model.Book;
import com.nttdata.model.Customer;
import com.nttdata.model.Product;
import com.nttdata.service.CustomerService;

import reactor.core.publisher.Mono;

@Component
public class CustomerHandler {

	private final CustomerService customerService;
	
	static Mono<ServerResponse> notFound = ServerResponse.notFound().build();

    @Autowired
    public CustomerHandler(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    
    public Mono<ServerResponse> getAllCustomer(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerService.findAllCustomer().log("Func: "), Customer.class);
    }
    
    public Mono<ServerResponse> getOneCustomer(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<Customer> itemMono = customerService.findByCustomerId(id);
        return itemMono.flatMap(item ->
                ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(fromValue(item))
                        .switchIfEmpty(notFound));

    }
    
    public Mono<ServerResponse> createCustomer(ServerRequest serverRequest) {
        Mono<Customer> bookMono = serverRequest.bodyToMono(Customer.class);

        return bookMono.flatMap(customer ->
           ServerResponse.status(HttpStatus.CREATED)
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(customerService.createCustomer(customer), Customer.class));

    }
    
    
   
    
    
}
