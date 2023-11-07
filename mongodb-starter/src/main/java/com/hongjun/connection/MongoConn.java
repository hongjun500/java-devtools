package com.hongjun.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConn {

    public static final String uri = "mongodb://admin:admin@localhost:27018";
    public static final String databaseName = "test";

    public static MongoDatabase connect() {
        MongoDatabase database;
        MongoClient mongoClient = MongoClients.create(uri);

        database = mongoClient.getDatabase(databaseName);

        return database;
    }
}
