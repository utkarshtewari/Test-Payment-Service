package payments.event;

import java.io.Serializable;

public class PaymentCanceledEvent implements Serializable{

	private static final long serialVersionUID = 1372668997779003522L;
	private String accountNumber;
	private String paymentId;
	
	public PaymentCanceledEvent(){}
	
	public PaymentCanceledEvent(String paymentId, String accountNumber){
    	this.accountNumber = accountNumber;
		this.paymentId = paymentId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getPaymentId() {
		return paymentId;
	}
	
	@Override
	public String toString(){
		return "PaymentCanceledEvent: accountNumber:"+this.accountNumber+ " paymentId:"+ this.paymentId;
	}

}
