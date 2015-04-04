package com.gurps.cmdsocial.persistence;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.Assert;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.model.User;

/**
 * Custom persistence implementation in addition to that offered by Spring
 * repositories.
 * 
 * @author gurpiarbassi
 *
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

	private final MongoOperations mongoTemplate;

	@Autowired
	public UserRepositoryImpl(MongoOperations mongoTemplate) {

		Assert.notNull(mongoTemplate, "mongoTemplate not supplied");
		this.mongoTemplate = mongoTemplate;
	}

	@Override
	/**
	 * Find all the posts for this user and all the user he/she is following
	 * The collection of results is returned back in reverse chronological order
	 */
	public Collection<Post> showWall(final User user) {
		List<String> usernames = new ArrayList<>();
		usernames.add(user.getUsername());
		usernames.addAll(user.getSubscriptions());

		Query searchPosts = new Query(Criteria.where("username").in(usernames)).with(new Sort(
				Sort.Direction.DESC, "id"));
		Collection<Post> posts = mongoTemplate.find(searchPosts, Post.class);

		return posts;
	}

}
