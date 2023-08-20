package com.bank;

import java.util.*;
import com.custom_exceptions.ValidateCustomerNameException;
import com.custom_exceptions.ValidatePanNumberException;
import com.custom_exceptions.ValidatePhoneNumberException;
import java.sql.*;

public class App {
	public static void main (String args[]) throws Exception 
	{
		String name, panNo;
		long phnNo;
		int panNo_flag, name_flag, phnNo_flag;
		
		String db_name = null;
		long db_phnNo=0, db_accNo= 0;
		double db_balance=0.0;
		int db_accType=0;
		
		Scanner sc = new Scanner(System.in);
		
		// load driver
		Class.forName("org.h2.Driver");
		
		// create connection
		Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
		
		// create statement
		Statement st = con.createStatement();
		
		//database tables cmd
		
		System.out.println("Welcome to the Bank of JCI...");
		
		Customer customer = new Customer();
		
		do{
			System.out.println("Enter your PAN number: ");
			panNo = sc.next();
			customer.setPanNo(panNo);
			panNo_flag=0;
			try {
				customer.panNumberCheck();
			}
			catch(ValidatePanNumberException e) {
				panNo_flag=1;
				System.out.println(e);
			}
			
		} while(panNo_flag==1);
		
		String search= "SELECT * FROM BANK1 WHERE PAN_NO = '"+panNo+"'";
		
		ResultSet rs = st.executeQuery(search);
		
		while (rs.next()) {
			db_accType=rs.getInt("ACCOUNT_TYPE");
			db_balance=rs.getDouble("BALANCE");
			db_accNo=Long.parseLong(rs.getString("ACCOUNT_NO"));
			db_name=rs.getString("NAME");
			db_phnNo=Long.parseLong(rs.getString("PHONE_NO"));
		}
		
		
		if (db_accType==1) {
			System.out.println("You already have a savings account in JCI");
			System.out.println("Your account details are as follows: Account No: "+db_accNo+", Name: "+db_name+", Balance: "+db_balance+", Phone Number: "+db_phnNo);
			
			customer.setName(db_name);
			customer.setPanNo(panNo);
			customer.setPhnNo(db_phnNo);
			
			SavingsTransactions savingsTransaction = new SavingsTransactions();
			savingsTransaction.setAccountBalance(db_balance);
			savingsTransaction.setCustomerDetails(customer);
			savingsTransaction.doTransactions();
		}
		
		
		else if (db_accType==2) {
			System.out.println("You already have a current account in JCI");
			System.out.println("Your account details are as follows: Account No: "+db_accNo+", Name: "+db_name+", Balance: "+db_balance+", Phone Number: "+db_phnNo);
			
			customer.setName(db_name);
			customer.setPanNo(panNo);
			customer.setPhnNo(db_phnNo);
			
			CurrentTransactions ct = new CurrentTransactions();
			ct.setAccountBalance(db_balance);
			ct.setCustomerDetails(customer);
			ct.doTransactions();
		}
		
		
		else {
			customer.setPanNo(panNo);
			
			do {
				System.out.println("Enter your name: ");
				name= sc.next();
				customer.setName(name);
				name_flag=0;
				try {
					customer.customerNameCheck();
				}
				catch(ValidateCustomerNameException e) {
					name_flag=1;
					System.out.println(e);
				}
			} while (name_flag==1);
			
			customer.setName(name);
			
			do{
				System.out.println("Enter your phone number");
				phnNo = sc.nextLong();
				customer.setPhnNo(phnNo);
				phnNo_flag=0;
				try {
					customer.phoneNumberCheck();
				}
				catch(ValidatePhoneNumberException e) {
					phnNo_flag=1;
					System.out.println(e);
				}
			} while(phnNo_flag==1);
			
			customer.setPhnNo(phnNo);	
			
			System.out.println("Enter the type of account you want to create. Press 1 for Savings Account. Press 2 for Current Account.");
			int account=sc.nextInt();
			
			//db insertion has to be done for a new customer with input values and the default bank values values

			if (account == 1) {			
				SavingsTransactions savingsTransaction = new SavingsTransactions();
				savingsTransaction.setCustomerDetails(customer);
				savingsTransaction.doTransactions();
			}
			
			else {
				CurrentTransactions ct = new CurrentTransactions();
				ct.setCustomerDetails(customer);
				ct.doTransactions();
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		
	

		
		
		
		
		
		
		
		st.close();
		con.close();
		sc.close();
	}
}
