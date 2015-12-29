/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Retrieving Specific Documents from MongoDB Using Java.
package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

/**
 *
 * @author soham
 */
public class findSpecificDocument {
    public static void main(String[] args) {
    try {
      MongoClient mongoClient = new MongoClient("localhost", 27017);
      DB db = mongoClient.getDB("words");
      DBCollection collection = db.getCollection("word_stats");
      findSpecificDocument.over12(collection);
      findSpecificDocument.startingABC(collection);
      findSpecificDocument.startEndVowels(collection);
      findSpecificDocument.over6Vowels(collection);
      findSpecificDocument.nonAlphaCharacters(collection);
    } catch (Exception e) {
      //System.out.println(e);
    }
  }
  public static void displayCursor(DBCursor cursor){
    String words = "";
    while(cursor.hasNext()){
      DBObject doc = cursor.next();
      words = words.concat(doc.get("word").toString()).concat(",");
    }
    if(words.length() > 65){
      words = words.substring(0, 65) + "...";
    }
    System.out.println(words);
  }
  public static void over12(DBCollection collection){
    BasicDBObject query = 
        new BasicDBObject("size", 
            new BasicDBObject("$gt", 12));
    DBCursor cursor = collection.find(query);
    System.out.println("\nWords with more than 12 characters: ");
    findSpecificDocument.displayCursor(cursor);
  }
  public static void startingABC(DBCollection collection){
    BasicDBObject query = 
        new BasicDBObject("first", 
            new BasicDBObject("$in", new String[]{"a","b","c"}));
    DBCursor cursor = collection.find(query);
    System.out.println("\nWords starting with A, B or C: ");
    findSpecificDocument.displayCursor(cursor);
  }
  public static void startEndVowels(DBCollection collection){
    BasicDBObject query = 
        new BasicDBObject("$and", new BasicDBObject[]{
            new BasicDBObject("first", 
                new BasicDBObject("$in", 
                    new String[]{"a","e","i","o","u"})),
            new BasicDBObject("last", 
                new BasicDBObject("$in", 
                    new String[]{"a","e","i","o","u"}))
    });
    query.append("size", new BasicDBObject("$gt", 5));
    DBCursor cursor = collection.find(query);
    System.out.println("\nWords starting and ending with a vowel: ");
    findSpecificDocument.displayCursor(cursor);
  }
  public static void over6Vowels(DBCollection collection){
    BasicDBObject query = 
        new BasicDBObject("stats.vowels", 
            new BasicDBObject("$gt", 5));
    DBCursor cursor = collection.find(query);
    System.out.println("\nWords with more than 5 vowels: ");
    findSpecificDocument.displayCursor(cursor);
  }
  public static void nonAlphaCharacters(DBCollection collection){
    BasicDBObject query = 
      new BasicDBObject("charsets", 
          new BasicDBObject("$elemMatch",
              new BasicDBObject("$and", new BasicDBObject[]{
                  new BasicDBObject("type", "other"),
                  new BasicDBObject("chars", 
                      new BasicDBObject("$size", 1))
    })));
    DBCursor cursor = collection.find(query);
    System.out.println("\nWords with 1 non-alphabet characters: ");
    findSpecificDocument.displayCursor(cursor);
  }
    
}
