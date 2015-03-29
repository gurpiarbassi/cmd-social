package com.gurps.cmdsocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;

import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;

/**
 * Main configuration class.
 * 
 * @author gurpiarbassi
 *
 */
@ComponentScan
@EnableAutoConfiguration
@ConfigurationProperties(prefix = "environments.dev.database")
@EnableMongoRepositories(basePackages = "com.gurps.cmdsocial.persistence")

public class Application {

	private String ip;
	private int port;
	private String name;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp() {
		return this.ip;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(destroyMethod = "destroy")
	public MongoDbFactory mongoDbFactory() throws Exception {
		Mongo mongo = new EmbeddedMongoBuilder().version("2.4.5").bindIp(getIp())
		.port(getPort()).build();
		return new SimpleMongoDbFactory(mongo, getName());
	}
	
	public @Bean MongoOperations mongoTemplate(Mongo mongo) throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
		return mongoTemplate;
	}
	
	
}
