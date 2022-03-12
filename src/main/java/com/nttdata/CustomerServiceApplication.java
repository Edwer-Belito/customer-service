package com.nttdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * anotacion de aplicacion boot.
 */
@SpringBootApplication
/**
 * anotacion habilita como cliente de aureka.
 */
@EnableEurekaClient
public class CustomerServiceApplication {

/**
 * metodo de inicializacion del boot.
 */
public static void main(final String[] args) {
SpringApplication.run(CustomerServiceApplication.class, args);
}
}
