package com.gurps.cmdsocial.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.gurps.cmdsocial.domain.Timeline;

@Service
public interface SocialService {

	void post(String user, String message);
	
	Timeline read(String user);
	
	void follow(String user, String userToFollow);
	
	Collection<Timeline> showWall(String user);
	
}
