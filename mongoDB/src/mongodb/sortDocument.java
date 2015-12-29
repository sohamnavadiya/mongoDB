/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Sorting Documents Represented by a Cursor in Java.

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
public class sortDocument {
     public static void main(String[] args) {
    try {
      MongoClient mongoClient = new MongoClient("localhost", 27017);
      DB db = mongoClient.getDB("words");
      DBCollection collection = db.getCollection("word_stats");
      sortDocument.sortWordsAscending(collection);
      sortDocument.sortWordsDesc(collection);
      sortDocument.sortWordsAscAndSize(collection);
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
  public static void sortWordsAscending(DBCollection collection) {
    BasicDBObject query = new BasicDBObject("first", "w");
    DBCursor cursor = collection.find(query);
    BasicDBObject sorter = new BasicDBObject("word", 1);
    cursor.sort(sorter);
    System.out.println("\nW words ordered ascending: ");
    sortDocument.displayCursor(cursor);
  }
  public static void sortWordsDesc(DBCollection collection) {
    BasicDBObject query = new BasicDBObject("first", "w");
    DBCursor cursor = collection.find(query);
    BasicDBObject sorter = new BasicDBObject("word", -1);
    cursor.sort(sorter);
    System.out.println("\nW words ordered descending: ");
    sortDocument.displayCursor(cursor);
  }
  public static void sortWordsAscAndSize(DBCollection collection) {
    BasicDBObject query = new BasicDBObject("first", "q");
    DBCursor cursor = collection.find(query);
    BasicDBObject sorter = new BasicDBObject("last", 1);
    sorter.append("size", -1);
    cursor.sort(sorter);
    System.out.println("\nQ words ordered first by last letter " +
                       "and then by size: ");
    sortDocument.displayCursor(cursor);
  }
    
}
