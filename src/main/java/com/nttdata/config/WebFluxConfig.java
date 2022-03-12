package com.nttdata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * anotacion configuracion.
 */
@Configuration
/**
 * abilita app reactiva.
 */
@EnableWebFlux
public class WebFluxConfig  implements WebFluxConfigurer {
}
