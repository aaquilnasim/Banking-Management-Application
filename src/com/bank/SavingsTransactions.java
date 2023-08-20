package com.bank;

import java.util.*;

import com.custom_exceptions.LowBalanceException;
import com.custom_exceptions.TransactionLimitExceededException;

public class SavingsTransactions {
	
	int choice;
	Scanner sc = new Scanner(System.in);
	Savings s= new Savings();
	Customer customer;
	
	public void setAccountBalance(double x) {
		s.setAccountBalance(x);
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
					try {
						s.deposit(dep);
					}
					catch (TransactionLimitExceededException e) {
						System.out.println(e);
					}
					
					break;
					
				case 2:
					System.out.println("Enter the amount you want to withdraw");
					double with = sc.nextDouble();
					try {
					s.withDraw(with);
					}
					catch(TransactionLimitExceededException e) {
						System.out.println(e);
					}
					catch (LowBalanceException e) {
						System.out.println(e);
					}
					break;
					
				case 3: 
					System.out.println(customer);
					s.showDetails();
					break;
					
				case 4:
					s.transactionCount();
					break;
					
				case 5:
					System.out.println("Bye...");
					break;
			}	
		} while(choice!=5);
	}
	
	
}

