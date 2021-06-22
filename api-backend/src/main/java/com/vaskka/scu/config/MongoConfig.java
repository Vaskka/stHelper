package com.vaskka.scu.config;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;

/**
 * @author rusheng
 */
@Configuration
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private Integer mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;
    
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create(new ConnectionString(String.format("mongodb://%s:%d", mongoHost, mongoPort)));
    }

    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }

}
