package com.gurps.cmdsocial.persistence;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.Assert;

import com.gurps.cmdsocial.model.Post;

public class UserRepositoryImpl implements UserRepositoryCustom {

	private final MongoOperations operations;

	@Autowired
	public UserRepositoryImpl(MongoOperations operations) {

		Assert.notNull(operations, "MongoOperations not supplied");
		this.operations = operations;
	}

	@Override
	public Collection<Post> showWall(String username) {
		// get all posts for this user along with the posts of all the people
		// he/she is following and return them
		// in chronological order
		// need username, message and time it was posted relative to now
		return null;
	}

}
