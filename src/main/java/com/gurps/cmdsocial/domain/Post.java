package com.gurps.cmdsocial.domain;

import java.util.Date;

import lombok.Data;

import org.springframework.data.mongodb.core.mapping.Field;

@Data
/**
 * Encapsulates a posting on the timeline
 * @author gurpiarbassi
 *
 */
public class Post {

	@Field("msg")
	private String message;
	
	@Field("created")
	private Date creationDateTime; //Mongodb doesnt seem to support Java 8 dates. Could create custom convertors but we'll go with the legacy Date for now :( 
}
