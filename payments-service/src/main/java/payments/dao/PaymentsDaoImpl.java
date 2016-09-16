package payments.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import payments.model.Payment;

@Repository
public class PaymentsDaoImpl extends JdbcDaoSupport implements PaymentsDao {
	private static final Logger LOG = LoggerFactory.getLogger(PaymentsDaoImpl.class);
	
	@Autowired
    DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Payment> getAllPayments() {
		// TODO Auto-generated method stub
		
		LOG.info("Retrieving payments");
		
		List<Payment> payments = new ArrayList<Payment>();
				
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String paymentsQuery = "Select payment_id, account_number, customer_id, customer_name, amount, status from payments_view";
		
		jdbcTemplate.query(paymentsQuery, 
				(rs, rowNum) -> new Payment(rs.getString("payment_id"),
				rs.getString("account_number"),
				rs.getString("customer_id"),
				rs.getString("customer_name"),
				rs.getDouble("amount"),
				rs.getString("status")))
		.forEach(payment -> payments.add(payment));
		
		LOG.info("Retrieved payments:"+payments.size());
		
		return payments;
	}

}
