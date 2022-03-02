package com.nttdata.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer {

	@Id
	private String idCustomer = UUID.randomUUID().toString().substring(0, 10);
	private String name;
	private String customerType;
	private List<Product> products;
	
	
}
