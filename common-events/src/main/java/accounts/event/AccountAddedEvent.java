package accounts.event;

import java.io.Serializable;

public class AccountAddedEvent implements Serializable{

	private static final long serialVersionUID = -6372745591589889030L;
	private String accountNumber;
	private String customerName;
	private String customerId;
	private double balance;
	
	public AccountAddedEvent(){}
	
	public AccountAddedEvent(String accountNumber, String customerId, 
			String customerName, double balance){
    	this.accountNumber = accountNumber;
    	this.customerName = customerName;
		this.customerId = customerId;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	@Override
	public String toString(){
		return "AccountAddedEvent: accountNumber:"+this.accountNumber+ " balance:"+ this.balance;
	}
}
