package com.gurps.cmdsocial.persistence;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.gurps.cmdsocial.model.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	
	/**
	 * 
	 * @param userId the username to seach for
	 * @param sort the sort order
	 * @return Collection of Posts belonging to the given user
	 */
	@Query("{ username:?0 }")
	Collection<Post> findByUserId(String userId, Sort sort);
}
