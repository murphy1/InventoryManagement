package com.invman.products;

import java.util.ArrayList;
import java.util.HashMap;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

public class ProductDatabase{
	
	MongoClient mongoClient = new MongoClient("localhost", 27017);
	MongoDatabase db = mongoClient.getDatabase("products");

	public void addProduct(String collectionName, HashMap<String, String> data) {
		
		int documentCounter = 0;
		
		MongoCollection<Document> collection = db
	            .getCollection(collectionName);
		
		Document product = null;
		
		// Get the data from the HashMap and add it to the db
		for(String key : data.keySet()) {
			String value = data.get(key);
			
			if(documentCounter == 0) {
				product = new Document(key, value);
				documentCounter++;
			}
			else if(documentCounter > 0) {
				product.append(key, value);
			}
		}
		
		documentCounter = 0;
		
		collection.insertOne(product);
		
		mongoClient.close();
	}
	
	public String searchProduct(String collectionName, String productType, String query) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		Bson bsonFilter = Filters.eq(productType, query);
		FindIterable<Document> findIt = collection.find(bsonFilter);
		
		return findIt.first().toString();
	}
	
	public void removeProduct(String collectionName, String productType, String name) {
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		Bson bsonFilter = Filters.eq(productType, name);
		
		collection.deleteOne(bsonFilter);
	}
	
	public ArrayList<String> listProducts(String collectionName){
		ArrayList<String> products = new ArrayList<String>();
		
		MongoCollection<Document> collection = db.getCollection(collectionName);
		
		FindIterable<Document> findIterable = collection.find(new Document());
		
		Block<Document> getBlock = new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        products.add(document.toString());
		    }
		};
		
		findIterable.forEach(getBlock);
		
		return products;
	}

}
