package com.nttdata.route;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.nttdata.handler.CustomerHandler;


@Configuration
public class CustomerRoute {
	
	@Bean
    public RouterFunction<ServerResponse> bookRouterFunc(CustomerHandler customerHandler){
        return RouterFunctions.route(GET("/customer/func/getAll").and(accept(MediaType.TEXT_EVENT_STREAM))
                ,customerHandler::getAllCustomer)
                .andRoute(GET("/customer/func/get/"+"{id}").and(accept(MediaType.APPLICATION_JSON))
                        ,customerHandler::getOneCustomer)
                .andRoute(POST("/customer/func/create").and(accept(MediaType.APPLICATION_JSON))
                        ,customerHandler::createCustomer);


    }

}
