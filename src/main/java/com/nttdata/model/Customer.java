package com.nttdata.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * para los gets y sets.
 */
@Data
/**
 * para contructor sin argumentos.
 */
@AllArgsConstructor
/**
 * para constructor con argumentos.
 */
@NoArgsConstructor
/**
 * para indicar el nombre de la coleccion en mongo db.
 */
@Document(collection = "customer")
/**
 * Una clase para un cliente del banco.
 * @version 1.0, 10/03/2022
 * @author Edwer Belito
 */
public class Customer {

/**
 * id generado para un cliente nuevo.
 */
@Id
/**
 * id cliente.
 */
private String idCustomer = UUID.randomUUID().toString()
.substring(0, BigDecimal.TEN.intValue());
/**
 * nombre cliente.
 */
private String name;
/**
 * tipo cliente.
 */
private String customerType;
/**
 * lista productos del cliente.
 */
private List<Product> products;
}
