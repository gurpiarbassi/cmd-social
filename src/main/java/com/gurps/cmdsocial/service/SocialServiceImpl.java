package com.gurps.cmdsocial.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.model.User;
import com.gurps.cmdsocial.persistence.UserRepository;

@Component
public class SocialServiceImpl implements SocialService {

	@Autowired
	private UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(SocialServiceImpl.class);

	@Override
	public void post(final String username, final String message) {
		LOGGER.debug("creating post for user " + username + " with message " + message);

		User user = getUser(username);

		Post post = new Post();
		post.setMessage(message);
		Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		post.setCreationDateTime(now);

		Collection<Post> posts = user.getPosts();
		posts.add(post);

		user.setUsername(username);
		user.setPosts(posts);
		userRepository.save(user);

	}

	@Override
	public User read(final String user) {
		LOGGER.debug("reading timeline for user " + user);
		return userRepository.findByUsername(user);
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
	public Collection<Post> showWall(String user) {
		LOGGER.debug("showing wall of user " + user);
		return null;
	}

	private User getUser(final String username) {
		User user = this.read(username);
		if (user == null) {
			user = new User();
		}
		return user;
	}

}
