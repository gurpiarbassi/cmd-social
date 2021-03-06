package com.gurps.cmdsocial.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import com.gurps.cmdsocial.model.Post;
import com.gurps.cmdsocial.service.SocialService;
import com.gurps.cmdsocial.ui.formatter.PostFormatter;

@Controller
/**
 * Command line controller class used to consume user input
 * and route the request to the appropriate service call.
 * @author gurpiarbassi
 *
 */
public class SocialController implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(SocialController.class);
	private static final String WALL_REGEX = "\\w+\\s{1}wall";
	private static final String FOLLOW_REGEX = "\\w+\\s{1}follows\\s{1}\\w+";
	private static final String READ_REGEX = "\\w+";
	private static final String POST_REGEX = "\\w+\\s{1}->\\s{1}.+";

	@Autowired
	private SocialService socialService;

	@Autowired
	@Qualifier(value = "basicFormatter")
	private PostFormatter postFormatter;

	@Override
	public void run(String... arg0) throws Exception {
		LOGGER.info("Welcome to cmd-social. Please enter command below");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				String text = br.readLine();
				this.processCommand(text);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

	}

	/**
	 * Processes the input command by making the appropriate service call.
	 * 
	 * @param command
	 *            String expression for the input command
	 */
	protected void processCommand(final String command) {
		String[] tokens = command.split(" ");

		if (command.matches(WALL_REGEX)) {
			LOGGER.debug("opting to view users wall");
			Collection<Post> wall = socialService.showWall(tokens[0]);
			wall.forEach(post -> System.out.println(postFormatter.formatPost(post, true)));

		} else if (command.matches(FOLLOW_REGEX)) {
			LOGGER.debug("opting follow user");
			socialService.follow(tokens[0], tokens[2]);

		} else if (command.matches(POST_REGEX)) {
			LOGGER.debug("opting to post");
			tokens = command.split("\\s{1}->\\s{1}");
			socialService.post(tokens[0], tokens[1]);
		} else if (command.matches(READ_REGEX)) {
			LOGGER.debug("opting to read users timeline");
			Collection<Post> posts = socialService.read(tokens[0]);

			LOGGER.debug("Timeline retrieved = " + posts);
			posts.forEach(post -> System.out.println(postFormatter.formatPost(post, false)));
		} else {
			LOGGER.warn("Please enter a valid command!");
		}
	}
}
