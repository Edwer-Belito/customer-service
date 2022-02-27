package com.nttdata.model;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customer")
@ToString
public class Customer {

	@Id
	public String idCustomer = UUID.randomUUID().toString().substring(0, 10);
	public String name;
	public String customerType;
	public Product product;
	
}
