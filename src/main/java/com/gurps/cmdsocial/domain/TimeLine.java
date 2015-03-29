package com.gurps.cmdsocial.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document(collection="Timeline")
@Data
/**
 * Encapsulates a users timeline
 * @author gurpiarbassi
 *
 */
public class Timeline {
	
		@Id
		private String id;
		
		@NotBlank
		@Field("user")
		@Indexed(unique = true, sparse = true)
		private String username;	
		
		@Field("posts")
		private Collection<Post> posts = new ArrayList<>();
		
		private Set<String> subscribers = new HashSet<>();
}
