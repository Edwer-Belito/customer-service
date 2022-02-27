package com.nttdata.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customer")
public class Customer {

	@Id
	public String idCustomer = UUID.randomUUID().toString().substring(0, 10);
	public String name;
	public String customerType;
	public Product product;
	
}
