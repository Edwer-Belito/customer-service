package com.nttdata.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.model.Customer;
import com.nttdata.model.Product;
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
    public Mono<Customer> createEmp (@RequestBody Customer customer){
		return customerService.createCustomer(customer);
    }

    @GetMapping(value = "/getAll",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flux<Customer> findAll(){
        return customerService.findAllCustomer();
    }


    @GetMapping("/get/{id}")
    @ResponseBody
    public ResponseEntity<Mono<Customer>> findById(@PathVariable("id") String id){
       Mono<Customer> employeeMono= customerService.findByCustomerId(id);
        return new ResponseEntity<Mono<Customer>>(employeeMono,employeeMono != null? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }
    
    /*
     * Endpoint para actualizar saldo del cliente
     */
    @PutMapping("/updateSaldo/{idCustomer}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ResponseEntity<Customer>> updateSaldo (@RequestBody Mono<Product>product,@PathVariable("idCustomer") String idCustomer){
		return customerService.updateSaldo(idCustomer,product)
				.flatMap(customer -> Mono.just(ResponseEntity.ok(customer)))
				.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	
    }
    
}
