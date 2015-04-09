package com.gurps.cmdsocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.MongoDbFactory;
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
@EnableConfigurationProperties
@Configuration
@ConfigurationProperties(locations = "classpath:application.yml", prefix = "environments.dev.database")
@EnableMongoRepositories(basePackages = "com.gurps.cmdsocial.persistence")
public final class Application {

	private String ip;

	private int port;

	private String name;

	private MongoDbFactory mongoDbFactory;

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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(destroyMethod = "destroy")
	public MongoDbFactory mongoDbFactory() throws Exception {
		if (mongoDbFactory == null) {
			Mongo mongo = new EmbeddedMongoBuilder().version("2.4.5").bindIp(getIp())
					.port(getPort()).build();
			mongoDbFactory = new SimpleMongoDbFactory(mongo, getName());
		}
		return mongoDbFactory;
	}

	@Bean
	@DependsOn("mongoDbFactory")
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(this.mongoDbFactory);
	}

}
