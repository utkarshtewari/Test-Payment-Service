package payments.model;

import java.io.Serializable;

public class PaymentRequest implements Serializable {
	
	private static final long serialVersionUID = -7856007389516138779L;
	private String paymentId;
	private String customerName;
	private String customerId;
	private String accountNumber;
	private double amount;
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString(){
		return "account:"+this.accountNumber+ "customerName:"+ this.customerName
				+" customerId:"+this.customerId+ " amount:"+this.amount;
	}

}
