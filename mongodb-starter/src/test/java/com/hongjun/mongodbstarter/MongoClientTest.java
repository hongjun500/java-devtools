package com.hongjun.mongodbstarter;

import com.hongjun.connection.MongoConn;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Projections;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;

public class MongoClientTest {

   private MongoDatabase database;


    @Test
    void testClientConn(){
        MongoConn mongoConn = new MongoConn();
        database = mongoConn.connect();

        MongoCollection<Document> topSpotifySongs = database.getCollection("top_spotify_songs");
        Bson fields = Projections.fields(Projections.excludeId());
        FindIterable<Document> documents = topSpotifySongs.find().projection(fields);
        documents = documents.limit(100);
        for (Document document : documents) {
            System.out.println(document.toJson());
        }
    }
}
