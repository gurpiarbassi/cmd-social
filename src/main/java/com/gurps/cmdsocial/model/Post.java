package com.gurps.cmdsocial.model;

import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
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
	
	@Field("userid")
	private String userId;
	
	@Field("msg")
	private String message;
	
	public LocalDateTime getPostDate(){
		ObjectId objId = new ObjectId(this.getId());
		return LocalDateTime.ofInstant(objId.getDate().toInstant(), ZoneId.systemDefault());
	}
}
