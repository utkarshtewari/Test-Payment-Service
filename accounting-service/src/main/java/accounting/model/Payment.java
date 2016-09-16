package accounting.model;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Payment {
	
	@Id
    private String paymentId;
    private String accountNumber;
    private String customerId;
    private String customerName;
    private double amount;
    private String status;
    
    public Payment() {
    }

    public Payment(String paymentId, String accountNumber, 
    		String customerId, String customerName,
    		double amount, String status) {
        this.paymentId = paymentId;
        this.accountNumber = accountNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.amount = amount;
        this.status = status;
    }

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
	
    

}
