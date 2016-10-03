package accounting.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class UpdateAccountCommand {
	
	@TargetAggregateIdentifier
	private final String accountNumber;
	private final String customerName;
	private final String customerId;
	private final Double amount;

	public UpdateAccountCommand(String accountNumber, 
    		String customerId, String customerName, double amount){
    	this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.customerId = customerId;
		this.amount = amount;
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

	public Double getAmount() {
		return amount;
	}
	
}
