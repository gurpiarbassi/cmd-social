package com.gurps.cmdsocial.persistence;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gurps.cmdsocial.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
   Collection<Post> findByUserId(String userId);
}
