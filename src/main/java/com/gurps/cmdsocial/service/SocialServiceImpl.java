package com.gurps.cmdsocial.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gurps.cmdsocial.domain.Post;
import com.gurps.cmdsocial.domain.Timeline;
import com.gurps.cmdsocial.persistence.TimelineRepository;

@Component
public class SocialServiceImpl implements SocialService{

	@Autowired
	private TimelineRepository timelineRepository;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SocialServiceImpl.class);
	
	@Override
	public void post(final String user, final String message) {
		LOGGER.debug("creating post for user " + user + " with message " + message);
		
		Timeline timeline = timelineRepository.findByUsername(user);
		//if user already has a timeline then add to existing one or create new one.
		
		if(timeline == null){
			timeline = new Timeline();
		}
		
		Post post = new Post();
		post.setMessage(message);
		Date now = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
		post.setCreationDateTime(now);
		
		Collection<Post> posts = timeline.getPosts();
		posts.add(post);
		
		timeline.setUsername(user);
		timeline.setPosts(posts);
		timelineRepository.save(timeline);
		
	}

	@Override
	public Timeline read(final String user) {
		LOGGER.debug("reading timeline for user " + user);
		return timelineRepository.findByUsername(user);
	}

	@Override
	public void follow(final String user, final String userToFollow) {
		LOGGER.debug("user " + user + " is subscribing to follow user " + userToFollow);
		
	}

	@Override
	public Collection<Timeline> showWall(String user) {
		LOGGER.debug("showing wall of user " + user);
		return null;
	}

}
