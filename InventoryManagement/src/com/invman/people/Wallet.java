package com.invman.people;

public class Wallet {
	
	private String username;
	private Double balance;
	
	UserDatabase db = new UserDatabase();
	
	public Wallet(String username, Double balance) {
		this.username = username;
		this.balance = balance;
	}
	
	public void createWallet(String username) {
		db.createWallet(username);
	}
	
	public void deposit(Double amount) {
		db.updateWallet("deposit", username, amount);
	}
	
	public void withdraw(Double amount) {
		db.updateWallet("withdraw", username, amount);
	}
	
	public String getBalance(String username) {
		return db.walletBalance(username);
	}

}
