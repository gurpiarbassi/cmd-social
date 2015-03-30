package com.gurps.cmdsocial.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.model.User;
import com.gurps.cmdsocial.persistence.PostRepository;
import com.gurps.cmdsocial.persistence.UserRepository;

@Component
public class SocialServiceImpl implements SocialService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(SocialServiceImpl.class);

	@Override
	public void post(final String username, final String message) {
		LOGGER.debug("creating post for user " + username + " with message " + message);

		User user = getUser(username);
		user.setUsername(username);
		User persistedUser = userRepository.save(user); //TODO do we really want to save before checking whether this is a new object?
		
		Post post = new Post();
		post.setMessage(message);
		post.setUserId(persistedUser.getId());
		postRepository.save(post);

		//TODO look into txn mgmt using two phase commit for mongo since mongo txns only atomic at document level.
	}

	@Override
	public Collection<Post> read(final String username) {
		LOGGER.debug("reading timeline for user " + username);
		User user = getUser(username);
		if(user.getId() != null){
			return postRepository.findByUserId(user.getId()); //TODO username or object id
		}
		return Collections.<Post>emptyList();
		
	}

	@Override
	public void follow(final String username, final String userToFollow) {
		LOGGER.debug("user " + username + " is subscribing to follow user " + userToFollow);
		User user = getUser(username);
		user.setUsername(username);

		Set<String> subscribers = user.getSubscribers();
		subscribers.add(userToFollow); // we are assuming the user to follow
										// already exists as per requirement!
		userRepository.save(user);
	}

	@Override
	public Collection<Post> showWall(final String user) {
		LOGGER.debug("showing wall of user " + user);
		return null;
	}

	private User getUser(final String username) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			user = new User();
		}
		return user;
	}

}
