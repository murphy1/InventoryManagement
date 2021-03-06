package com.invman.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.invman.people.User;
import com.invman.people.UserDatabase;
import com.invman.people.Wallet;
import com.invman.products.Electronics;
import com.invman.products.Furniture;
import com.invman.products.Games;
import com.invman.products.Grocery;
import com.invman.products.Product;

public class UserInterface {
	
	User user = new User();
	Wallet wallet = new Wallet();
	UserDatabase userDb = new UserDatabase();
	
	ArrayList<String> products = new ArrayList<String>();
	
	Scanner scanner;
	Boolean checkForInput = true;
	String currentUser = "";
	
	public UserInterface() {
		this.scanner = new Scanner(System.in);
		products.add("Grocery");
		products.add("Electronics");
		products.add("Furniture");
		products.add("Games");
	}
	
	public void start() {
		
		loginMenu();
		
	}
	
	private void loginMenu() {
		
		while(checkForInput == true) {
			System.out.println("Enter your username:");
			String username = scanner.nextLine();
			
			String result = userDb.searchUsernameForLogin("User", username);
			if(result == "User does not exist!") {
				System.out.println(result);
			}
			else {
				currentUser = result;
				checkForInput = false;
			}
		}
		checkForInput = true;
		userMenu();
	}
	
	private void userMenu() {
		
		while(checkForInput == true) {
			System.out.println("[1] Create a user");
			System.out.println("[2] Search a user");
			System.out.println("[3] List all users");
			System.out.println("[4] Delete a user");
			System.out.println("[5] Create a wallet (current user)");
			System.out.println("[6] Update wallet (current user)");
			System.out.println("[7] Exit");
			
			int option = scanner.nextInt();
			scanner.nextLine();
			
			if(option == 1) {
				System.out.println("Enter your first name:");
				String fname = scanner.nextLine();
				
				System.out.println("Enter your last name:");
				String lname = scanner.nextLine();
				
				System.out.println("Choose a username:");
				String username = scanner.nextLine();
				
				user.add(fname, lname, username);
			}
			else if(option == 2) {
				System.out.println("Choose a username:");
				String username = scanner.nextLine();
				user.searchUser(username);
			}
			else if(option == 3) {
				user.listUsers();
			}
			else if(option == 4) {
				System.out.println("Choose a username:");
				String username = scanner.nextLine();
				user.remove(username);
			}
			else if(option == 5) {
				wallet.createWallet(currentUser);
			}
			else if(option == 6) {
				System.out.println("withdrawal or deposit:");
				String result = scanner.nextLine();
				
				System.out.println("Amount:");
				Double amount = scanner.nextDouble();
				
				if(result.equals("withdrawal")) {
					wallet.withdraw(currentUser, amount);
				}
				else if(result.equals("deposit")) {
					wallet.deposit(currentUser, amount);
				}
			}
			else if(option == 7) {
				checkForInput = false;
			}
		}
		checkForInput = true;
		productMenu();
	}
	
	private void productMenu() {
		while(checkForInput == true) {
			
			System.out.println("Please choose one of the options:");
			for(String product : products) {
				System.out.println(product);
			}
			String isProduct = scanner.nextLine();
			if(products.contains(isProduct)) {
				
				System.out.println("[1] Add a product");
				System.out.println("[2] Search for a product");
				System.out.println("[3] List all products");
				System.out.println("[4] Delete a product");
				System.out.println("[5] Exit");
				
				int option = scanner.nextInt();
				scanner.nextLine();
				
				if(option == 1) {
					
					if(isProduct.equals("Grocery")) {
						System.out.println("Grocery name:");
						String groceryName = scanner.nextLine();
						System.out.println("Grocery price:");
						Double groceryPrice = scanner.nextDouble();
						System.out.println("Grocery expiration (mm/dd/yyyy):");
						scanner.nextLine();
						String groceryExpiration = scanner.nextLine();
						
						Product groceryAdd = new Grocery(groceryName, groceryPrice, groceryExpiration);
						groceryAdd.add();
						
						wallet.withdraw(currentUser, groceryPrice);
					}
					else if(isProduct.equals("Electronics")) {
						System.out.println("Brand Name:");
						String electronicBrandName = scanner.nextLine();
						System.out.println("Price:");
						Double electronicPrice = scanner.nextDouble();
						System.out.println("Name:");
						scanner.nextLine();
						String electronicName = scanner.nextLine();
						
						Product electronicAdd = new Electronics(electronicBrandName, electronicName, electronicPrice);
						electronicAdd.add();
						
						wallet.withdraw(currentUser, electronicPrice);
					}
					else if(isProduct.equals("Furniture")) {
						System.out.println("Store Name:");
						String furnitureStoreName = scanner.nextLine();
						System.out.println("Price:");
						Double furniturePrice = scanner.nextDouble();
						System.out.println("Name:");
						String furnitureName = scanner.nextLine();
						System.out.println("Room:");
						scanner.nextLine();
						String roomName = scanner.nextLine();
						
						Product furnitureAdd = new Furniture(furnitureStoreName, furnitureName, roomName, furniturePrice);
						furnitureAdd.add();
						
						wallet.withdraw(currentUser, furniturePrice);
					}
					else if(isProduct.equals("Games")) {
						System.out.println("Platform:");
						String platform = scanner.nextLine();
						System.out.println("Price:");
						Double gamePrice = scanner.nextDouble();
						System.out.println("Name:");
						scanner.nextLine();
						String gameName = scanner.nextLine();
						
						Product gameAdd = new Games(platform, gameName, gamePrice);
						gameAdd.add();
						
						wallet.withdraw(currentUser, gamePrice);
					}
					
				}
				else if(option == 2) {					
					if(isProduct.equals("Grocery")){
						System.out.println("Choose a product:");
						String productSearch = scanner.nextLine();
						
						Product grocerySearch = new Grocery();
						System.out.println(grocerySearch.searchProductbyName(productSearch));
					}
					else if(isProduct.equals("Electronics")) {
						System.out.println("Choose a product:");
						String productSearch = scanner.nextLine();
						
						Product electronicSearch = new Electronics();
						System.out.println(electronicSearch.searchProductbyName(productSearch));
					}
					else if(isProduct.equals("Furniture")) {
						System.out.println("Choose a product:");
						String productSearch = scanner.nextLine();
						
						Product furnitureSearch = new Furniture();
						System.out.println(furnitureSearch.searchProductbyName(productSearch));
					}
					else if(isProduct.equals("Games")) {
						System.out.println("Choose a product:");
						String productSearch = scanner.nextLine();
						
						Product gameSearch = new Games();
						System.out.println(gameSearch.searchProductbyName(productSearch));
					}
				}
				else if(option == 3) {
					if(isProduct.equals("Grocery")) {
						Product groceryList = new Grocery();
						System.out.println(groceryList.listProducts());
					}
					else if(isProduct.equals("Electronics")) {
						Product electronicList = new Electronics();
						System.out.println(electronicList.listProducts());
					}
					else if(isProduct.equals("Furniture")) {
						Product furnitureList = new Furniture();
						System.out.println(furnitureList.listProducts());
					}
					else if(isProduct.equals("Games")) {
						Product gameList = new Games();
						System.out.println(gameList.listProducts());
					}
				}
				else if(option == 4) {
					if(isProduct.equals("Grocery")) {
						System.out.println("Choose a product:");
						String groceryToRemove = scanner.nextLine();
						Product groceryRemove = new Grocery();
						groceryRemove.remove(groceryToRemove);
					}
					else if(isProduct.equals("Electronics")) {
						System.out.println("Choose a product:");
						String electronicToRemove = scanner.nextLine();
						Product electronicRemove = new Electronics();
						electronicRemove.remove(electronicToRemove);
					}
					else if(isProduct.equals("Furniture")) {
						System.out.println("Choose a product:");
						String furnitureToRemove = scanner.nextLine();
						Product furnitureRemove = new Furniture();
						furnitureRemove.remove(furnitureToRemove);
					}
					else if(isProduct.equals("Games")) {
						System.out.println("Choose a product:");
						String gameToRemove = scanner.nextLine();
						Product gameRemove = new Games();
						gameRemove.remove(gameToRemove);
					}
				}
				else if(option == 5) {
					checkForInput = false;
				}
			}
			else {
				continue;
			}
			
		}
		checkForInput = true;
	}

}
