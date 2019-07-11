package com.invman.ui;

import java.util.Scanner;

import com.invman.people.User;

public class UserInterface {
	
	User user = new User();
	
	Scanner scanner;
	
	Boolean checkForInput = true;
	
	public UserInterface() {
		this.scanner = new Scanner(System.in);
	}
	
	public void start() {
		
		userMenu();
		
	}
	
	private void userMenu() {
		
		while(checkForInput == true) {
			System.out.println("[1] Create a user");
			System.out.println("[2] Search a user");
			System.out.println("[3] List all users");
			System.out.println("[4] Delete a user");
			System.out.println("[5] Exit");
			
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
				checkForInput = false;
			}
		}
		checkForInput = true;
	}

}
