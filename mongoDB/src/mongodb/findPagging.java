/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
//Paging Through Documents in a Collection Using Java.

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
public class findPagging {
     public static void main(String[] args) {
    try {
      MongoClient mongoClient = new MongoClient("localhost", 27017);
      DB db = mongoClient.getDB("words");
      DBCollection collection = db.getCollection("word_stats");
      findPagging.pageResults(collection, 0);
    } catch (Exception e){
      System.out.println(e);
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
  public static void pageResults(DBCollection collection, 
                                 Integer skip) {
    BasicDBObject query = new BasicDBObject("first", "w");
    DBCursor cursor = collection.find(query);
    cursor.sort(new BasicDBObject("word", 1));
    cursor.limit(10);
    cursor.skip(skip);
    System.out.println("Page " + new Integer(skip+1).toString() + 
                       " to " + 
                       new Integer(skip+cursor.size()).toString() + 
                       ":");
    findPagging.displayCursor(cursor);
    if (cursor.size() == 10){
      findPagging.pageResults(collection, skip+10);
    }
  }
    
}
