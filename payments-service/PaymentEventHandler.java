package payments.eventhandler;

import javax.sql.DataSource;

//import org.axonframework.domain.Message;
//import org.axonframework.eventhandling.annotation.EventHandler;
//import org.axonframework.eventhandling.annotation.Timestamp;
//import org.joda.time.DateTime;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//
//import payments.event.PaymentAddedEvent;
//import payments.event.PaymentScreenedEvent;
//import payments.event.PaymentAccountedEvent;
//import payments.event.PaymentCanceledEvent;


//@Component
public class PaymentEventHandler {
	
//	private static final Logger LOG = LoggerFactory.getLogger(PaymentEventHandler.class);
//	
//	@Autowired
//    DataSource dataSource;
//	
//	@SuppressWarnings("rawtypes")
//	@EventHandler
//    public void handlePaymentAddedEvent(PaymentAddedEvent event, 
//    		Message eventMessage, @Timestamp DateTime moment) {
//		
//		LOG.info("Received PaymentAddedEvent checking datasource:");
//		
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		
//		String paymentId = event.getPaymentId();
//		String accountNumber = event.getAccountNumber();
//		String customerId = event.getCustomerId();
//		String customerName = event.getCustomerName();
//		double amount = event.getAmount();
//		
//		String insertStatement = "INSERT INTO payments_view VALUES (?,?,?,?,?,?)";
//		
//		jdbcTemplate.update(insertStatement, new Object[]{paymentId, accountNumber, customerId, customerName, amount, "PENDING"});
//		
//		LOG.info("Inserted Payment for payment id:"+paymentId);
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@EventHandler
//    public void handlePaymentCanceledEvent(PaymentCanceledEvent event, 
//    		Message eventMessage, @Timestamp DateTime moment) {
//		
//		LOG.info("Received PaymentCanceledEvent checking datasource:");
//		
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		
//		String paymentId = event.getPaymentId();
//		//String accountNumber = event.getAccountNumber();
//		
//		String updateStatement = "UPDATE payments_view SET status = ? where payment_id = ? ";
//		jdbcTemplate.update(updateStatement, new Object[]{"CANCELED", paymentId});
//		LOG.info("Updated Payment for payment id:"+paymentId);
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@EventHandler
//    public void handlePaymentScreenedEvent(PaymentScreenedEvent event, 
//    		Message eventMessage, @Timestamp DateTime moment) {
//		
//		LOG.info("Received PaymentScreenedEvent for Payment Id:"+ event.getPaymentId());
//		
//		
//	}
//	
//	@SuppressWarnings("rawtypes")
//	@EventHandler
//    public void handlePaymentAccountedEvent(PaymentAccountedEvent event, 
//    		Message eventMessage, @Timestamp DateTime moment) {
//		
//		LOG.info("Received PaymentAccountedEvent for Payment Id:"+ event.getPaymentId());
//		
//		
//	}

}
