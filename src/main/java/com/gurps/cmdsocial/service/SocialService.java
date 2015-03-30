package com.gurps.cmdsocial.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.model.User;

@Service
public interface SocialService {

	void post(String user, String message);
	
	User read(String user);
	
	void follow(String user, String userToFollow);
	
	Collection<Post> showWall(String user);
	
}
