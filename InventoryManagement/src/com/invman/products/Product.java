package com.invman.products;

import java.util.ArrayList;

public interface Product {
	void add();
	void remove(String name);
	String searchProductbyName(String product);
	ArrayList<String> listProducts();
}
