package com.gurps.cmdsocial.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Encapsulates a user entity
 * @author gurpiarbassi
 *
 */
@Document(collection = "user")
public class User {

	@Id
	private String id;

	@NotBlank
	@Field("username")
	@Indexed(unique = true, sparse = true)
	private String username;

	@Field("subscriptions")
	private Set<String> subscriptions = new HashSet<>();

	public User(String username, Set<String> subscriptions) {
		this.username = username;
		this.subscriptions = subscriptions;
	}

	@PersistenceConstructor
	public User(String id, String username, Set<String> subscriptions) {
		this.id = id;
		this.username = username;
		this.subscriptions = subscriptions;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Set<String> getSubscriptions() {
		return subscriptions;
	}

}
