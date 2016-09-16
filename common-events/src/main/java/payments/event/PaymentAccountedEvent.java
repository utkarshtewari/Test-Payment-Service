package payments.event;

import java.io.Serializable;

public class PaymentAccountedEvent implements Serializable {

	private static final long serialVersionUID = 3563774683283352443L;
	/*private String accountNumber;
	private String customerName;
	private String customerId;
	private double amount;*/
	private String paymentId;
	private String status;
	
	public PaymentAccountedEvent(){}
	
	/*public PaymentAccountedEvent(String paymentId, String accountNumber, 
    		String customerId, String customerName, double amount, String status){
    	this.accountNumber = accountNumber;
		this.customerName = customerName;
		this.customerId = customerId;
		this.amount = amount;
		this.paymentId = paymentId;
		this.status = status;
	}*/
	
	public PaymentAccountedEvent(String paymentId, String status){
		this.paymentId = paymentId;
		this.status = status;
	}

	

	public String getPaymentId() {
		return paymentId;
	}
	
	public String getStatus() {
		return status;
	}
	
	
	@Override
	public String toString(){
		return "PaymentScreenedEvent: payment id"+this.paymentId+" status:"+ this.status;
	}

}
