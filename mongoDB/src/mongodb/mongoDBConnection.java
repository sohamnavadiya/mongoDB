/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
//Implementing the Java MongoDB Driver and Connecting to MongoDB Using Java

package mongodb;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;

/**
 *
 * @author soham
 */
public class mongoDBConnection {
     public static void main(String[] args) {
    try {
      MongoClient mongoClient = new MongoClient("localhost", 27017);
      mongoClient.setWriteConcern(WriteConcern.JOURNAL_SAFE);
      DB db = mongoClient.getDB("words");
      DBCollection collection = db.getCollection("word_stats");
      System.out.println("Number of Documents: " + 
        new Long(collection.count()).toString());
    } catch (Exception e) {
      System.out.println(e);
    }
  }
    
}
