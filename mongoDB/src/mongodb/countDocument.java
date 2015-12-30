/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//
//Counting the Number of Documents Represented by a Cursor in Java

package mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import java.util.ArrayList;

/**
 *
 * @author soham
 */
public class countDocument {
    public static void main(String[] args) {
    try {
      MongoClient mongoClient = new MongoClient("localhost", 27017);
      DB db = mongoClient.getDB("words");
      DBCollection collection = db.getCollection("word_stats");
      countDocument.countWords(collection);
    } catch (Exception e){
      System.out.println(e);
    }
  }
  public static void countWords(DBCollection collection) {
    Integer count = collection.find().count();
    System.out.println("Total words in the collection: " + count);
    ArrayList<Character> letter=new ArrayList<Character>();
    BasicDBObject query;
    /*query = new BasicDBObject("first", "a");
    count = collection.find(query).count();
    System.out.println("Total words starting with A: " + count);
    */
  
    for(int i=97;i<=122;i++){
        
        letter.add((char)i);
    } 
    String[] chr={"a","b","c","d","e","f","g","h"};   
    for(int i=0;i<letter.size();i++){ 
        query = new BasicDBObject("first", letter.get(i));
        count = collection.find(query).count();
        System.out.println("Total words starting with "+letter.get(i)+": " + count);          
    }  
  }   
}
