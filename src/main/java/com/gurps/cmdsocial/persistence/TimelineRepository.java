package com.gurps.cmdsocial.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gurps.cmdsocial.domain.TimeLine;

@Repository
public interface TimelineRepository extends MongoRepository<TimeLine, String>{
	
}
