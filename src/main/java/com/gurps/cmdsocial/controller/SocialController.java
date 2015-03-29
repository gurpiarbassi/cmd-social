package com.gurps.cmdsocial.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
@Controller
public class SocialController implements CommandLineRunner{

	private static final Logger LOGGER = LoggerFactory.getLogger(SocialController.class);
	
	@Override
	public void run(String... arg0) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			try {
		         String text = br.readLine();
		         System.out.println("you typed =  " + text);
		      } catch (IOException ioe) {
		         System.out.println("IO error trying to read your name!");
		         System.exit(1);
		      }
		}
		
	}

}
