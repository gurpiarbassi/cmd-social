package com.gurps.cmdsocial.ui.formatter;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.gurps.cmdsocial.TestApplicationContext;
import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.service.SocialService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationContext.class)
public class TestPostFormatter {

	@Autowired
	private SocialService socialService;

	@Test
	public void testNonUserAwareFiveSecsAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofSeconds(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, false);
		assertEquals("this is a test message (5 seconds ago)", output);
	}

	@Test
	public void testNonUserAwareFiveMinutesAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofMinutes(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, false);
		assertEquals("this is a test message (5 minutes ago)", output);
	}

	@Test
	public void testNonUserAwareFiveHoursAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofHours(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, false);
		assertEquals("this is a test message (5 hours ago)", output);
	}

	@Test
	public void testNonUserAwareFiveDaysAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofDays(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, false);
		assertEquals("this is a test message (5 days ago)", output);
	}

	@Test
	public void testNonUserAwareFiveMonthsAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Period.ofMonths(5))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, false);
		assertEquals("this is a test message (5 months ago)", output);
	}

	@Test
	public void testNonUserAwareFiveYearsAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Period.ofYears(5))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, false);
		assertEquals("this is a test message (5 years ago)", output);
	}

	@Test
	public void testUserAwareFiveSecsAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofSeconds(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, true);
		assertEquals("gurps - this is a test message (5 seconds ago)", output);
	}

	@Test
	public void testUserAwareFiveMinutesAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofMinutes(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, true);
		assertEquals("gurps - this is a test message (5 minutes ago)", output);
	}

	@Test
	public void testUserAwareFiveHoursAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofHours(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, true);
		assertEquals("gurps - this is a test message (5 hours ago)", output);
	}

	@Test
	public void testUserAwareFiveDaysAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Duration.ofDays(5l))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, true);
		assertEquals("gurps - this is a test message (5 days ago)", output);
	}

	@Test
	public void testUserAwareFiveMonthsAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Period.ofMonths(5))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, true);
		assertEquals("gurps - this is a test message (5 months ago)", output);
	}

	@Test
	public void testUserAwareFiveYearsAgoPost() throws Exception {
		Post post = socialService.post("gurps", "this is a test message");
		Date fakePostDate = Date.from(LocalDateTime.now().minus(Period.ofYears(5))
				.atZone(ZoneId.systemDefault()).toInstant());
		post.setId(new ObjectId(fakePostDate).toString());

		PostFormatter formatter = new PostFormatterImpl();
		String output = formatter.formatPost(post, true);
		assertEquals("gurps - this is a test message (5 years ago)", output);
	}

}
