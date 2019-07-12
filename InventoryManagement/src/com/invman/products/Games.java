package com.invman.products;

import java.util.ArrayList;
import java.util.HashMap;

public class Games implements Product{
	
	ProductDatabase db = new ProductDatabase();
	
	HashMap<String, String> details = new HashMap<String, String>();
	
	public Games() {
		
	}
	
	public Games(String platform, String name, Double price) {
		details.put("Platform", platform);
		details.put("Name", name);
		details.put("Price", Double.toString(price));
	}

	@Override
	public void add() {
		db.addProduct("Games", details);
	}

	@Override
	public void remove(String name) {
		db.removeProduct("Games", "Name", name);
	}

	@Override
	public String searchProductbyName(String product) {
		return db.searchProduct("Games", "Name", product);
	}

	@Override
	public ArrayList<String> listProducts() {
		return db.listProducts("Games");
	}

}
