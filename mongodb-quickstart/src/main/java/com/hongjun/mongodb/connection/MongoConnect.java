package com.hongjun.mongodb.connection;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;


@Slf4j
public class MongoConnect {
    public static final String URI = "mongodb://mongodb:mongodb@localhost:27017/?authSource=admin";

    private final MongoClient mongoClient;

    public MongoConnect() {
        ConnectionString connectionString = new ConnectionString(URI);
        this.mongoClient = MongoClients.create(connectionString);
    }

    public MongoDatabase getDatabase(String db) {
        MongoDatabase database = mongoClient.getDatabase(db);
        Bson command = new BsonDocument("ping", new BsonInt64(1));
        Document commandResult = database.runCommand(command);
        log.info("Pinged your deployment. You successfully connected to MongoDB!");
        return database;
    }
}
