package com.hongjun.connection;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoConn {

    public static final String uri = "mongodb://localhost:27017";
    public static final String databaseName = "test";

    public MongoDatabase connect() {
        MongoDatabase database;
        MongoClient mongoClient = MongoClients.create(uri);

        database = mongoClient.getDatabase(databaseName);
        Bson command = new BsonDocument("ping", new BsonInt64(1));
        Document commandResult = database.runCommand(command);
        System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
        return database;
    }
}
