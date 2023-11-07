package com.hongjun.mongodbstarter;

import com.hongjun.connection.MongoConn;
import com.hongjun.crud.MongoCrudExample;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.BsonInt64;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;

public class MongoClientTest {

   private MongoDatabase database;

   private final MongoCrudExample mongoCrudExample;

    public MongoClientTest() {
        mongoCrudExample = new MongoCrudExample();
    }


    @Test
     void testClientConn(){
        database = MongoConn.connect();
        Bson command = new BsonDocument("ping", new BsonInt64(1));
        Document commandResult = database.runCommand(command);
        System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
    }

    @Test
    void insert(){
        mongoCrudExample.save();
        mongoCrudExample.saves();
    }

    @Test
    void del(){
        mongoCrudExample.delOne("item","mousePad");
        mongoCrudExample.delMany();
    }
}
