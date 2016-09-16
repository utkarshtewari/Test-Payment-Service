package payments.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class AddPaymentCommand {
	@TargetAggregateIdentifier
	private final String paymentId;
    private final String accountNumber;
	private final String customerName;
	private final String customerId;
	private final Double amount;
    
    public AddPaymentCommand(String paymentId, String accountNumber, 
    		String customerId, String customerName, double amount){
    	this.paymentId = paymentId;
    	this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.customerId = customerId;
		this.amount = amount;
	}
	
	public String getPaymentId() {
		return paymentId;
	}

	public String getCustomerName() {
		return customerName;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	
	public double getAmount() {
		return amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
