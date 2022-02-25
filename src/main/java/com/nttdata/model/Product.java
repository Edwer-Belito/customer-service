package com.nttdata.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	public String code;
	public String name;
	public String type;
	public double saldo;
	
}
