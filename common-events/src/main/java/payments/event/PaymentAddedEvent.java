package payments.event;

import java.io.Serializable;

public class PaymentAddedEvent implements Serializable {

	private static final long serialVersionUID = 7204224132402494486L;
	private String accountNumber;
	private String customerName;
	private String customerId;
	private double amount;
	private String paymentId;
	
	public PaymentAddedEvent(){}
	
	public PaymentAddedEvent(String paymentId, String accountNumber, 
    		String customerId, String customerName, double amount){
    	this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.customerId = customerId;
		this.amount = amount;
		this.paymentId = paymentId;
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

	public double getAmount() {
		return amount;
	}

	public String getPaymentId() {
		return paymentId;
	}
	
	@Override
	public String toString(){
		return "PaymentAddedEvent: accountNumber:"+this.accountNumber+ " customerName:"+ this.customerName
				+" customerId:"+this.customerId+ " amount:"+this.amount;
	}
}
