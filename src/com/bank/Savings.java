package com.bank;

import com.custom_exceptions.LowBalanceException;
import com.custom_exceptions.TransactionLimitExceededException;

class Savings extends Account{
	int tCount=0;
	long acc_no;
	
	double balance=getMinBalance();
	
	public void setAccountBalance(double x) {
		balance=x;
	}

	double getRateOfInterest() {
		return 10.0;
	}
	
	double getMinBalance(){
		return 5000.0;
	}
	
	
	
	void deposit(double x) throws TransactionLimitExceededException {
			if (tCount < 5) {
				balance=x+balance;
				System.out.println("Amount deposited: "+x);
				System.out.println("Your account balance is: "+balance);
				tCount+=1;
			}
			else {
				throw new TransactionLimitExceededException("Sorry!! Your transaction limit has been exceeded for the month");
		}
	}
	
	
	void withDraw(double x) throws LowBalanceException, TransactionLimitExceededException {
		if ((balance-x) < getMinBalance() && tCount < 5) {
			throw new LowBalanceException("Warning! JCI's Savings Account Balance cannot be less than 5000/.");
		}
		else if (tCount < 5) {
			balance=balance-x;
			System.out.println("Amount withdrawn: "+x);
			System.out.println("Your account balance is: "+balance);
			tCount+=1;
			}
		else {
			throw new TransactionLimitExceededException("Sorry!! Your transaction limit has been exceeded for the month");
		}
	}
	
	
	void transactionCount() {
		System.out.println("Transaction performed: "+tCount);
		System.out.println("Trasaction left: "+(5-tCount));
	}
	
	
	void showDetails() {
		System.out.println("Your account balance is: "+balance);
		System.out.println("Trasaction left: "+(5-tCount));
	}
}