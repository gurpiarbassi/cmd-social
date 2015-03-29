package com.gurps.cmdsocial.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gurps.cmdsocial.domain.Timeline;

@Repository
/**
 * Repository for persistence of user timelines
 * @author gurpiarbassi
 *
 */
public interface TimelineRepository extends MongoRepository<Timeline, String>,  TimelineRepositoryCustom{
	
	Timeline findByUsername(String username);
}
