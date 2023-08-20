package com.bank;

import java.util.*;

import com.custom_exceptions.LowBalanceException;

public class CurrentTransactions {
	
	int choice;
	
	Scanner sc = new Scanner(System.in);
	Current c = new Current();
	Customer customer;
	
	public void setAccountBalance(double x) {
		c.setAccountBalance(x);
	}
	
	public void setCustomerDetails(Customer customer) {
		this.customer = customer;
	}
	
	public void doTransactions() {
		do {
			System.out.println("1. Deposit amount.");
			System.out.println("2. Withdraw amount.");
			System.out.println("3. Show Account details.");
			System.out.println("4. Show Transactions details.");
			System.out.println("5. Exit.");
			choice=sc.nextInt();
			
			switch (choice) {
				case 1: 
					System.out.println("Enter the amount you want to deposit");
					double dep = sc.nextDouble();
					c.deposit(dep);
					break;
					
				case 2:
					System.out.println("Enter the amount you want to withdraw");
					double with = sc.nextDouble();
					try {
						c.withDraw(with);
					}
					catch(LowBalanceException e) {
						System.out.println(e);
					}
					break;
					
				case 3: 
					System.out.println(customer);
					c.showDetails();
					break;
					
				case 4:
					c.transactionCount();
					break;
					
				case 5:
					System.out.println("Bye...");
					break;
			}
				
		} while(choice!=5);
	}
	}


