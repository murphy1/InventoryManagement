package com.invman.people;

import java.util.ArrayList;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
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
		boolean usernameTaken = userExists(username);
		
		if(usernameTaken == true) {
			System.out.println("Username already exists!");
		}
		else if(usernameTaken == false) {
			MongoCollection<Document> collection = db
					.getCollection(collectionName);
			
			Document newUser = new Document("First Name", fname)
					.append("Last Name", lname)
					.append("Username", username);

			collection.insertOne(newUser);
		}
	}
	
	public String searchUserByUsername(String collectionName, String username) {
		String result = "";
		
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		Bson bsonFilter = Filters.eq("Username", username);
		FindIterable<Document> findIt = collection.find(bsonFilter);
		
		try {
			result = findIt.first().toString();
		}catch(NullPointerException e) {
			e.getMessage();
		}
		
		if(result.isEmpty()) {
			result = "User does not exist!";
		}
		else {
			return result;
		}
		return result;
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
		
		if(userCheck == true) {
			BasicDBObject newWallet = new BasicDBObject();
			newWallet.append("$set", new BasicDBObject().append("Wallet", 0.0));
			BasicDBObject user = new BasicDBObject().append("Username", username);

			collection.updateOne(user, newWallet);
		}
		else {
			System.out.println("User does not exist");
		}
	}
	
	public String updateWallet(String queryCheck, String username, Double amount) {
		
		boolean userCheck = userExists(username);
		String result = "";
		
		if(userCheck == true) {
			
			MongoCollection<Document> collection = db.getCollection("User");
			Bson bsonFilter = Filters.eq("Username", username);
			FindIterable<Document> findIt = collection.find(bsonFilter);
			
			Double currentAmountInWallet = Double.parseDouble(findIt.first().get("Wallet").toString());
			
			if(queryCheck.equals("deposit")) {
				currentAmountInWallet = currentAmountInWallet + amount;
			}
			else if(queryCheck.equals("withdraw")) {
				currentAmountInWallet = currentAmountInWallet - amount;
			}
			
			BasicDBObject newWallet = new BasicDBObject();
			newWallet.append("$set", new BasicDBObject().append("Wallet", currentAmountInWallet));
			BasicDBObject user = new BasicDBObject().append("Username", username);

			collection.updateOne(user, newWallet);
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
	
	private boolean userExists(String username) {
		boolean result = false;
		String userCheck = "";
		
		MongoCollection<Document> collection = db.getCollection("User");
				
		Bson bsonFilter = Filters.eq("Username", username);
		FindIterable<Document> findIt = collection.find(bsonFilter);
		
		try {	
			userCheck = findIt.first().get("Username").toString();
		}catch(NullPointerException e) {
			e.getMessage();
		}
		
		if(userCheck.isEmpty()) {
			result = false;
		}
		else if(userCheck.length() > 0) {
			result = true;
		}
		
		return result;
	}
}
