/*
 * Clash Horn - MIT License
 */
package com.clashhorn.infrastructure.spring.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**
 *
 * @author morgade
 */
@Configuration
public class MongoDBConfig extends AbstractMongoConfiguration {
    @Value("localhost")
    private String host;
    @Value("27017")
    private int port;
    @Value("clash-horn")
    private String databaseName;
    
    @Override
    protected String getDatabaseName() {
        return databaseName;
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient( new ServerAddress(host, port) );
    }

}
