package com.hongjun.quickstart.mongo.mongodb.connection;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.extern.log4j.Log4j2;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;


@Log4j2
public class MongoConn {
    private static final String URI = "mongodb://mongodb:mongodb@localhost:27017/?authSource=admin";

    private final MongoClient mongoClient;

    public MongoConn() {
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
