package com.invman.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Grocery implements Product{
	
	ProductDatabase db = new ProductDatabase();
	
	HashMap<String, String> details = new HashMap<String, String>();
	
	public Grocery() {
		
	}
	
	public Grocery(String name, Double price, String expiration) {
		details.put("Grocery", name);
		details.put("Price", Double.toString(price));
		details.put("Exp Date", expiration);
	}
	
	@Override
	public void add() {
		db.addProduct("Groceries", details);
	}
	
	@Override
	public void remove(String name) {
		db.removeProduct("Groceries", "Grocery", name);
	}

	@Override
	public ArrayList<String> listProducts() {
		return db.listProducts("Groceries");
	}

	@Override
	public String searchProductbyName(String product) {
		return db.searchProduct("Groceries", "Grocery", product);
	}

}
