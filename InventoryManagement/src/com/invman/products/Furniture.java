package com.invman.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Furniture implements Product{
	
	ProductDatabase db = new ProductDatabase();
	
	HashMap<String, String> details = new HashMap<String, String>();
	
	public Furniture() {
		
	}
	
	public Furniture(String store, String name, String room, Double price) {
		details.put("Store", store);
		details.put("Name", name);
		details.put("Room", room);
		details.put("Price", Double.toString(price));
	}

	@Override
	public void add() {
		db.addProduct("Furniture", details);
	}

	@Override
	public void remove(String name) {
		db.removeProduct("Furniture", "Name", name);
	}

	@Override
	public String searchProductbyName(String product) {
		return db.searchProduct("Furniture", "Name", product);
	}

	@Override
	public ArrayList<String> listProducts() {
		return db.listProducts("Furniture");
	}

}
