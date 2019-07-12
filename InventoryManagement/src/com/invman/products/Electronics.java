package com.invman.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Electronics implements Product{
	
	ProductDatabase db = new ProductDatabase();
	
	HashMap<String, String> details = new HashMap<String, String>();
	
	public Electronics() {
		
	}
	
	public Electronics(String brand, String name, Double price) {
		details.put("Brand", brand);
		details.put("Name", name);
		details.put("Price", Double.toString(price));
	}

	@Override
	public void add() {
		db.addProduct("Electronics", details);
	}

	@Override
	public void remove(String name) {
		db.removeProduct("Electronics", "Name", name);
	}

	@Override
	public String searchProductbyName(String product) {
		return db.searchProduct("Electronics", "Name", product);
	}

	@Override
	public ArrayList<String> listProducts() {
		return db.listProducts("Electronics");
	}
	
	

}
