package com.gurps.cmdsocial.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gurps.cmdsocial.MockApplicationContext;
import com.gurps.cmdsocial.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MockApplicationContext.class)
public class TestUserRepository {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testUsersCannotBeDuplicated() {
		try {
			userRepository.save(new User("gurps", new HashSet<String>()));
			userRepository.save(new User("gurps", new HashSet<String>()));
			fail("Duplicate key should have been detected!!");
		} catch (DuplicateKeyException dke) {

		}
	}

	@Test
	public void testSubscriptionsPersisted() {
		userRepository.save(new User("gurps", new HashSet<String>()));
		User user = userRepository.findByUsername("gurps");
		user.getSubscriptions().add("Fred");
		user.getSubscriptions().add("Bob");
		userRepository.save(user);

		User savedUser = userRepository.findByUsername("gurps");
		assertEquals(2, savedUser.getSubscriptions().size());
	}

}
