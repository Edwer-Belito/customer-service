package com.nttdata.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Customer;
import com.nttdata.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmp (@RequestBody Customer customer){
		customerService.createCustomer(customer);
    }

    @GetMapping(value = "/getAll",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Customer> findAll(){
        return customerService.findAllCustomer();
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Mono<Customer>> findById(@PathVariable("id") Integer id){
       Mono<Customer> employeeMono= customerService.findByCustomerId(id);
        return new ResponseEntity<Mono<Customer>>(employeeMono,employeeMono != null? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }
}
