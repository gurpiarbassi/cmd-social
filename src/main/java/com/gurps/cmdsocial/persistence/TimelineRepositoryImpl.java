package com.gurps.cmdsocial.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.util.Assert;

public class TimelineRepositoryImpl implements TimelineRepositoryCustom{

		private final MongoOperations operations;

		@Autowired
		public TimelineRepositoryImpl(MongoOperations operations) {

			Assert.notNull(operations, "MongoOperations not supplied");
			this.operations = operations;
		}

		
}
