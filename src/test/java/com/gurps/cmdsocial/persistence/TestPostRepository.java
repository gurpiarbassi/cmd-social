package com.gurps.cmdsocial.persistence;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gurps.cmdsocial.TestApplicationContext;
import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.service.SocialService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContext.class)
public class TestPostRepository {

	@Autowired
	private PostRepository postRepository;
	
	@Test
	public void testFindByUserLatestPostFirst() {
		postRepository.save(new Post("gurps", "message 1"));
		postRepository.save(new Post("gurps", "message 2"));
		Collection<Post> posts = postRepository.findByUserId("gurps", new Sort(Sort.Direction.DESC, "id"));
		
		assertEquals(2,  posts.size());
		Iterator<Post> iterator = posts.iterator();
		
		//should come back in reverse chrono order with latest first
		Post firstPost = iterator.next();
		Post secondPost = iterator.next();
		
		assertEquals("gurps", firstPost.getUserId());
		assertEquals("message 2", firstPost.getMessage());
		
		assertEquals("gurps", secondPost.getUserId());
		assertEquals("message 1", secondPost.getMessage());
		
	}
	
	@Test
	public void testFindByUserOldestPostFirst() {
		postRepository.save(new Post("gurps", "message 1"));
		postRepository.save(new Post("gurps", "message 2"));
		Collection<Post> posts = postRepository.findByUserId("gurps", new Sort(Sort.Direction.ASC, "id"));
		
		assertEquals(2,  posts.size());
		Iterator<Post> iterator = posts.iterator();
		
		//should come back in reverse chrono order with latest first
		Post firstPost = iterator.next();
		Post secondPost = iterator.next();
		
		assertEquals("gurps", firstPost.getUserId());
		assertEquals("message 1", firstPost.getMessage());
		
		assertEquals("gurps", secondPost.getUserId());
		assertEquals("message 2", secondPost.getMessage());
		
	}

}
