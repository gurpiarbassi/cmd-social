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
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {

	/**
	 * finds by username on the User entity
	 * @param username
	 * @return User with the given username
	 */
	User findByUsername(String username);
}
