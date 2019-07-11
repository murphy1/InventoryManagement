package com.invman.people;

import java.util.ArrayList;

public class User {
	
	private String fname;
	private String lname;
	private String username;
	
	UserDatabase db = new UserDatabase();
	
	public User(String fname, String lname, String username) {
		this.fname = fname;
		this.lname = lname;
		this.username = username;
	}

	public void add() {
		db.addUser("User", this.fname, this.lname, this.username);
	}

	public void remove(String username) {
		db.removeUser("User", username);
	}

	public String searchUser(String username) {
		return db.searchUserByUsername("User", username);
	}

	public ArrayList<String> listUsers() {
		return db.listUsers("User");
	}

}
