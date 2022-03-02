package com.nttdata.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	private String code;
	private String name;
	private String type;
	private BigDecimal saldo;
	
}
