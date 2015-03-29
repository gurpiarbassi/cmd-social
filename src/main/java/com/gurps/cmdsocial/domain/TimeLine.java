package com.gurps.cmdsocial.domain;

import java.util.Collection;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="Timeline")
@Data
public class TimeLine {
	
		@Id
		private String id;
		
		@NotBlank
		@Field("user")
		private String username;	
		
		@Field("posts")
		private Collection<Post> posts;
		
		private Collection<String> subscribers;
}
