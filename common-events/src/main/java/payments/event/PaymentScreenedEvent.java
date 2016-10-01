package payments.event;

import java.io.Serializable;

public class PaymentScreenedEvent implements Serializable {

	private static final long serialVersionUID = 1715660313663426413L;
	private String accountNumber;
	private String customerName;
	private String customerId;
	private double amount;
	private String paymentId;
	private String status;
	
	public PaymentScreenedEvent(){}
	
	public PaymentScreenedEvent(String paymentId, String accountNumber, 
    		String customerId, String customerName, double amount, String status){
    	this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.customerId = customerId;
		this.amount = amount;
		this.paymentId = paymentId;
		this.status = status;
	}
	
	public PaymentScreenedEvent(String paymentId, String status){
		this.paymentId = paymentId;
		this.status = status;
	}

	public String getPaymentId() {
		return paymentId;
	}
	
	public String getStatus() {
		return status;
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

	@Override
	public String toString(){
		//return "PaymentScreenedEvent: payment id"+this.paymentId+" status:"+ this.status;
		return "PaymentScreenedEvent: accountNumber:"+this.accountNumber+ " customerName:"+ this.customerName
				+" customerId:"+this.customerId+ " amount:"+this.amount;
	}
}
