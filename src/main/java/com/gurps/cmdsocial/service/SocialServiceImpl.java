package com.gurps.cmdsocial.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.model.User;
import com.gurps.cmdsocial.persistence.PostRepository;
import com.gurps.cmdsocial.persistence.UserRepository;

@Component
/**
 * Core service class for social networking operations.
 * @author gurpiarbassi
 *
 */
public class SocialServiceImpl implements SocialService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(SocialServiceImpl.class);

	@Override
	/**
	 * Posts a message to the timeline
	 * @param username The user whose timeline is being manipulated
	 * @param message The messag to post to the timeline
	 * @return the saved Post instance
	 */
	public Post post(final String username, final String message) {
		LOGGER.debug("creating post for user " + username + " with message " + message);

		User user = getUser(username);
		User persistedUser = userRepository.save(user); // TODO do we really
														// want to save before
														// checking whether this
														// is a new object?

		// TODO look into txn mgmt using two phase commit for mongo since mongo
		// txns are only atomic at document level.
		Post post = new Post(persistedUser.getUsername(), message);
		return postRepository.save(post);

	}

	@Override
	/**
	 * Finds all the posts by the given user and returns them in reverse chronological order
	 * @param username The username you which to query for posts
	 */
	public Collection<Post> read(final String username) {
		LOGGER.debug("reading timeline for user " + username);
		User user = getUser(username);
		if (user.getId() != null) {
			return postRepository.findByUserId(username, new Sort(Sort.Direction.DESC, "id"));
		}
		return Collections.<Post> emptyList();

	}

	@Override
	/**
	 * A user can follow another user
	 * @param username the current user
	 * @param userToFollow the userid of the user you wish to follow
	 */
	public void follow(final String username, final String userToFollow) {
		LOGGER.debug("user " + username + " is subscribing to follow user " + userToFollow);
		User user = getUser(username);
		Set<String> subscribers = user.getSubscriptions();
		subscribers.add(userToFollow); 
		// we are assuming the user to follow already exists as per requirement!
		userRepository.save(user);
	}

	@Override
	/**
	 * Every user has a wall which shows all the posts the user has committed
	 * and also any posts commited by their subscriptions
	 * @param user the userid of the user whose wal you want to see.
	 */
	public Collection<Post> showWall(final String user) {
		LOGGER.debug("showing wall of user " + user);
		return userRepository.showWall(getUser(user));
	}

	private User getUser(final String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			user = new User(username, new HashSet<String>());
		}
		return user;
	}

}
