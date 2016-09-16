package payments.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CancelPaymentCommand {
	
	@TargetAggregateIdentifier
	private final String paymentId;
	
	private final String accountNumber;
	
	public CancelPaymentCommand(String paymentId, String accountNumber){
    	this.paymentId = paymentId;
    	this.accountNumber = accountNumber;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}
	
	
}
