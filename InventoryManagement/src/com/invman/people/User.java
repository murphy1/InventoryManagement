package com.invman.people;

import java.util.ArrayList;

public class User {

	UserDatabase db = new UserDatabase();
	
	public User() {
		
	}

	public void add(String fname, String lname, String username) {
		db.addUser("User", fname, lname, username);
	}

	public void remove(String username) {
		db.removeUser("User", username);
	}

	public void searchUser(String username) {
		String user = db.searchUserByUsername("User", username);
		System.out.println(user);
	}

	public void listUsers() {
		ArrayList<String> users = db.listUsers("User");
		for(String user : users) {
			System.out.println(user);
		}
	}

}
