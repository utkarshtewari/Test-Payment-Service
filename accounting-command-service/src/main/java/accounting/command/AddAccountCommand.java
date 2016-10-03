package accounting.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class AddAccountCommand {
	
	@TargetAggregateIdentifier
	private final String accountNumber;
	private final String customerName;
	private final String customerId;
	private final Double balance;

	public AddAccountCommand(String accountNumber, 
    		String customerId, String customerName, double balance){
    	this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.customerId = customerId;
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public Double getBalance() {
		return balance;
	}

}
