package com.gurps.cmdsocial.persistence;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gurps.cmdsocial.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
   @Query("{ username:?0 }")
   Collection<Post> findByUserId(String userId, Sort sort);
}
