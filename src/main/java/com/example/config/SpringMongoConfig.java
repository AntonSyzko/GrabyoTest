package com.example.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Spring MongoDB configuration file
 * @port port for Mongo DB
 * @collection name - collection to store data
 * 
 */
public class SpringMongoConfig {
	String port = "127.0.0.1";
	String collectionName = "twitter_test";

	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
		MongoTemplate mongoTemplate = new MongoTemplate(new MongoClient(port),collectionName);
		return mongoTemplate;
	}
}