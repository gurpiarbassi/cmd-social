package com.gurps.cmdsocial.persistence;

import java.util.Collection;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.model.User;

/**
 * Custom functionality other than that provided by spring repositories
 * 
 * @author gurpiarbassi
 *
 */
public interface UserRepositoryCustom {

	/**
	 * 
	 * @param user the user whose wall you want to see
	 * @return Collection of posts belonging to the given user or any of his/her subscriptions
	 */
	Collection<Post> showWall(User user);
}
