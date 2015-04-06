package com.gurps.cmdsocial.service;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gurps.cmdsocial.TestApplicationContext;
import com.gurps.cmdsocial.model.Post;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContext.class)
public class TestSocialService {

	@Autowired
	private SocialService socialService;

	@Test
	public void testRead() {
		socialService.post("gurps", "this is a test message");
		socialService.post("gurps", "this is another test message");
		Collection<Post> posts = socialService.read("gurps");
		
		assertEquals(2,  posts.size());
		Iterator<Post> iterator = posts.iterator();
		
		//should come back in reverse chrono order with latest first
		Post firstPost = iterator.next();
		Post secondPost = iterator.next();
		
		assertEquals("gurps", firstPost.getUserId());
		assertEquals("this is another test message", firstPost.getMessage());
		
		assertEquals("gurps", secondPost.getUserId());
		assertEquals("this is a test message", secondPost.getMessage());
		
	}
	
	@Test
	public void testFollows() {
		socialService.post("gurps", "this is a test message");
		socialService.post("gurps", "this is another test message");
		socialService.post("fred", "this is freds message");
		socialService.follow("fred", "gurps");
		
		Collection<Post> posts = socialService.showWall("fred");
		
		assertEquals(3,  posts.size());
		Iterator<Post> iterator = posts.iterator();
		
		//should come back in reverse chrono order with latest first
		Post firstPost = iterator.next();
		Post secondPost = iterator.next();
		Post thirdPost = iterator.next();
		
		assertEquals("fred", firstPost.getUserId());
		assertEquals("this is freds message", firstPost.getMessage());
		
		assertEquals("gurps", secondPost.getUserId());
		assertEquals("this is another test message", secondPost.getMessage());
		
		assertEquals("gurps", thirdPost.getUserId());
		assertEquals("this is a test message", thirdPost.getMessage());
		
	}

}
