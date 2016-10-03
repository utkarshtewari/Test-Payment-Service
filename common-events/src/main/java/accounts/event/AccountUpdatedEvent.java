package accounts.event;

import java.io.Serializable;

public class AccountUpdatedEvent implements Serializable{

	private static final long serialVersionUID = 2664280629760008033L;
	private String accountNumber;
	private String customerName;
	private String customerId;
	private double amount;
	
	public AccountUpdatedEvent(){}
	
	public AccountUpdatedEvent(String accountNumber, String customerId, 
			String customerName, double amount){
    	this.accountNumber = accountNumber;
    	this.customerName = customerName;
		this.customerId = customerId;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	@Override
	public String toString(){
		return "AccountUpdatedEvent: accountNumber:"+this.accountNumber+ " amount:"+ this.amount;
	}

}
