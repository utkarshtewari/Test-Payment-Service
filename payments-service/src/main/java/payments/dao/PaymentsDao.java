package payments.dao;

import java.util.List;

import payments.model.Payment;

public interface PaymentsDao {
	
	public List<Payment> getAllPayments();

}
