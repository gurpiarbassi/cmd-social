package com.gurps.cmdsocial.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


/**
 * Encapsulates a posting on the timeline.
 * I originally had post as a embedded document on the User document.
 * However after analysis and thought I decided it ought to belong in its
 * own collection since mongodb has a document limit of 16MB and the number of 
 * posts/tweets can grow quite rapidly.
 * 
 * @author gurpiarbassi
 *
 */
@Document(collection="posts")
public class Post {
	
	@Id
	private String id;
	
	@Field("username")
	private String userId;
	
	@Field("message")
	private String message;
	
	public Post(String username, String message){
		this.userId = username;
		this.message = message;
	}
	
	@PersistenceConstructor
	public Post(String id, String userId, String message) {
		this.id = id;
		this.userId = userId;
		this.message = message;
	}
	
	public LocalDateTime getPostDate(){
		ObjectId objId = new ObjectId(this.id);
		return LocalDateTime.ofInstant(objId.getDate().toInstant(), ZoneId.systemDefault());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public String getMessage() {
		return message;
	}
	
}
