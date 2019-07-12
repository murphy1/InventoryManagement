package com.invman.people;

public class Wallet {
	
	UserDatabase db = new UserDatabase();
	
	public Wallet() {
		
	}
	
	public void createWallet(String username) {
		db.createWallet(username);
	}
	
	public void deposit(String username, Double amount) {
		db.updateWallet("deposit", username, amount);
	}
	
	public void withdraw(String username, Double amount) {
		db.updateWallet("withdraw", username, amount);
	}
	
	public String getBalance(String username) {
		return db.walletBalance(username);
	}

}
