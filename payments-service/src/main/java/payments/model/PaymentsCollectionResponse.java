package payments.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PaymentsCollectionResponse implements Serializable{
	
	private static final long serialVersionUID = 1135594284443943484L;
	private List<Payment> payments;

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
	public void addAccount(Payment payment) {
		if (this.payments == null) {
			this.payments = new ArrayList<Payment>();
		}
		this.payments.add(payment);
	}

}
