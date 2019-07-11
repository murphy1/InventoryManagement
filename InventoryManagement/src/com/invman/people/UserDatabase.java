package com.invman.people;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class UserDatabase {
	
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase db = mongoClient.getDatabase("users");
	
	public void addUser(String collectionName, String fname, String lname, String username) {
		
		// Add user check here and complete the method
		
		MongoCollection<Document> collection = db
				.getCollection(collectionName);
		
		// Check if username already exists in the database
		Bson bsonFilter = Filters.eq("Username", username);
		FindIterable<Document> findIt = collection.find(bsonFilter);
		
		// if username exists return alreasy exists
		String usernameCheck = findIt.first().toString();
		//else
		Document newUser = new Document("First Name", fname)
				.append("Last Name", lname)
				.append("Username", username);

		collection.insertOne(newUser);

		mongoClient.close();
		
	}
	
	public String searchUserByUsername(String collectionName, String username) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		Bson bsonFilter = Filters.eq("Username", username);
		FindIterable<Document> findIt = collection.find(bsonFilter);
		
		return findIt.first().toString();
	}
	
	public void removeUser(String collectionName, String username) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		Bson bsonFilter = Filters.eq("Username", username);
		
		collection.deleteOne(bsonFilter);
	}
	
	public ArrayList<String> listUsers(String collectionName){
		ArrayList<String> users = new ArrayList<String>();
		
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		FindIterable<Document> findIterable = collection.find(new Document());
		
		Block<Document> getBlock = new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        users.add(document.toString());
		    }
		};
		
		findIterable.forEach(getBlock);
		
		return users;
	}
	
	// Wallet Methods
	
	public void createWallet(String username) {
		
		MongoCollection<Document> collection = db.getCollection("User");
		
		boolean userCheck = userExists(username);
		//if true
		
		// Complete the method
		
		
		Document query = new Document();
        query.append("username","User");
        
        Document setData = new Document();
        setData.append("username", username).append("Wallet", 0);
        
        Document update = new Document();
        update.append("$set", setData);
        
        //To update single Document  
        collection.updateOne(query, update);
		
	}
	
	public String updateWallet(String queryCheck, String username, Double amount) {
		
		boolean userCheck = userExists(username);
		
		String result = "";
		
		if(userCheck == true) {
		
			if(queryCheck.equals("deposit")) {
				
				// Complete
				
			}
			else if(queryCheck.equals("withdraw")) {
				
				// Complete
				
			}
		}
		else {
			result = "No user exists";
		}
		return result;
	}
	
	public String walletBalance(String username) {
		
		boolean userCheck = userExists(username);
		
		String result = "";
		
		if(userCheck == true) {
			
			MongoCollection<Document> collection = db.getCollection("User");
			
			Bson bsonFilter = Filters.eq("Username", username);
			FindIterable<Document> findIt = collection.find(bsonFilter);
			
			result = findIt.first().getDouble("Wallet").toString();
			
		}
		else {
			result = "No user exists";
		}
		
		return result;
		
	}
	
	public boolean userExists(String username) {
		// Check to see if user exists
		MongoCollection<Document> collection = db.getCollection("User");
				
		Bson bsonFilter = Filters.eq("Username", username);
		FindIterable<Document> findIt = collection.find(bsonFilter);
				
		String userCheck = findIt.first().toString();
		
		return true;
	}
}
