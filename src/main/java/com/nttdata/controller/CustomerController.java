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

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * anotacion para publicar servicios.
 */
@RestController()
/**
 * anotacion para el request path.
 */
@RequestMapping("/customer")
/**
 * Una que expone servicios del cliente.
 * @version 1.0, 10/03/2022
 * @author Edwer Belito
 */
public class CustomerController {
/**
 * logger.
 */
private static Logger logger = LoggerFactory.
getLogger(CustomerController.class);

/**
 * injeccion.
 */
@Autowired
/**
 * service.
 */
private CustomerService customerService;

/**
 * post path method.
 * @return @PostMapping("/create")
 */
@PostMapping("/create")
/**
 * response code.
 */
@ResponseStatus(HttpStatus.CREATED)
/**
 * metodo crear empleado.
 * @param @RequestBody final Customer customer
 * @return Mono<Customer>
 */
public final Mono<Customer> createEmp(@RequestBody final Customer customer) {
return customerService.createCustomer(customer);
}

/**
 * get path method.
 * @return @GetMapping
 */
@GetMapping(value = "/getAll", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
/**
 * response status.
 */
@ResponseBody
public final Flux<Customer> findAll() {
return customerService.findAllCustomer();
}

/**
 * get path method.
 * @return @GetMapping
 */
@GetMapping("/get/{id}")
/**
 * response status.
 * @return @ResponseBody
 */
@ResponseBody
/**
 * findById method.
 * @param @PathVariable("id") final String id
 * @return @GetMapping
 * @throws InterruptedException si hay error
 */
public final Mono<ResponseEntity<Customer>> findById(@PathVariable("id") final
String id) throws InterruptedException {

logger.info("CustomerController - findById - IDCUSTOMER: {}", id);
// add timeout for resilience4j
TimeUnit.SECONDS.sleep(3);

return customerService.findByCustomerId(id).flatMap(customer -> Mono.just(
ResponseEntity.ok(customer))).switchIfEmpty(Mono.just(ResponseEntity.
notFound().build()));
}

/**
 * Endpoint para actualizar saldo del cliente.
 * @return @PutMapping
 */
@PutMapping("/updateSaldo/{idCustomer}")
/**
 * response status.
 * @return @ResponseStatus
 */
@ResponseStatus(HttpStatus.OK)
/**
 * updateSaldo.
 * @return Mono<ResponseEntity<Customer>>
 */
public final Mono<ResponseEntity<Customer>> updateSaldo(@RequestBody final
Mono<Product> product, @PathVariable("idCustomer") final String idCustomer) {
logger.info("CustomerController - updateSaldo - IDCUSTOMER: {} , "
+ "PRODUCT: {}", idCustomer, product);
return customerService.updateSaldo(idCustomer, product)
.flatMap(customer -> Mono.just(ResponseEntity.ok(customer)))
.switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
}

}
