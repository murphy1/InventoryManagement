package com.invman.products;

public class MainTesting {

	public static void main(String[] args) {
		Furniture furniture = new Furniture("Bed, Bath and Beyond", "Couch", "Living Room", 970.50);
		//furniture.add();
		
		//game.remove("Mordhau");
		
		System.out.println(furniture.searchProductbyName("Table"));
		
		//System.out.println(furniture.listProducts());

	}

}
