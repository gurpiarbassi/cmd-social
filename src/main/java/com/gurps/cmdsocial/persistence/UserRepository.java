package com.gurps.cmdsocial.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gurps.cmdsocial.model.User;

@Repository
/**
 * Repository for persistence of users
 * @author gurpiarbassi
 *
 */
public interface UserRepository extends MongoRepository<User, String>,  UserRepositoryCustom{
	
	User findByUsername(String username);
	
	
}
