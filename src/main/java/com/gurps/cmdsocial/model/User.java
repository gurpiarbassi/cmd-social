package com.gurps.cmdsocial.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection="user")
public class User {

	@Id
	private String id;
	
	@NotBlank
	@Field("user")
	@Indexed(unique = true, sparse = true)
	private String username;	
	
	@Field("subs")
	private Set<String> subscriptions = new HashSet<>();
}
