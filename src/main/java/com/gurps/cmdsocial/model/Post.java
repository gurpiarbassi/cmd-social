package com.gurps.cmdsocial.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
/**
 * Encapsulates a posting on the timeline
 * @author gurpiarbassi
 *
 */
@Document(collection="posts")
public class Post {
	
	@Id
	private String id;
	
	@Field("userid")
	private String userId;
	
	@Field("msg")
	private String message;
	
	public long getAgeInMillis(){
		//TODO need to calculate age in millis of post
		//Instant.now().minus(creationDateTime.toInstant().);
		return 1L;
	}
}
