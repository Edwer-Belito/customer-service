package com.nttdata.model;

import java.math.BigDecimal;

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
@NoArgsConstructor
/**
 * para constructor con argumentos.
 */
@AllArgsConstructor
/**
 * Una clase para un producto del banco.
 * @version 1.0, 10/03/2022
 * @author Edwer Belito
 */
public class Product {

/**
 * codigo del producto.
 */
private String code;
/**
 * nombre del producto.
 */
private String name;
/**
 * tipo del producto.
 */
private String type;
/**
 * saldo del producto.
 */
private BigDecimal saldo;

}
