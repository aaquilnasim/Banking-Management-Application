package com.bank;

import com.custom_exceptions.ValidateCustomerNameException;
import com.custom_exceptions.ValidatePanNumberException;
import com.custom_exceptions.ValidatePhoneNumberException;

public class Customer {
	
	private String name;
	private long phnNo;
	private String panNo;
	
	Customer(){
		
	}
	
	Customer(String name, long phnNo, String panNo){
		this.name=name;
		this.phnNo=phnNo;
		this.panNo=panNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void customerNameCheck() throws ValidateCustomerNameException {
		int count=0;
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isDigit(name.charAt(i)) && !Character.isLetter(name.charAt(i)) && !Character.isWhitespace(name.charAt(i))) {
			count++;
			}
		}
		
		if (count != 0)
			throw new ValidateCustomerNameException("Name contains special characters");
	}

	public long getPhnNo() {
		return phnNo;
	}

	public void setPhnNo(long phnNo) {
		this.phnNo = phnNo;
	}
	
	public void phoneNumberCheck() throws ValidatePhoneNumberException {
		if (String.valueOf(phnNo).length()!=10) {
			throw new ValidatePhoneNumberException("Phone Number entered by the user is invalid!!");
		}	
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	
	public void panNumberCheck() throws ValidatePanNumberException {
		if (panNo.length()!=10) {
			throw new ValidatePanNumberException("PAN Number entered by the user is invalid!!");
		}	
	}
	
	public String toString() {
		return "CUSTOMER DETAILS-> {Name: "+name+", Phone Number: "+phnNo+", Pan Number: "+panNo+"}";
	}
}
