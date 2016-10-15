package payments.handler;

import javax.sql.DataSource;

import org.axonframework.domain.Message;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import payments.event.PaymentAccountedEvent;
import payments.event.PaymentAddedEvent;
import payments.event.PaymentCanceledEvent;
import payments.event.PaymentScreenedEvent;
import accounts.event.AccountUpdatedEvent;


@Component
public class PaymentEventHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(PaymentEventHandler.class);
	
	@Autowired
    DataSource dataSource;
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handlePaymentAddedEvent(PaymentAddedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received PaymentAddedEvent checking datasource:");
		// TODO - Move the database updates to DAO
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String paymentId = event.getPaymentId();
			String accountNumber = event.getAccountNumber();
			String customerId = event.getCustomerId();
			String customerName = event.getCustomerName();
			double amount = event.getAmount();
			
			String insertStatement = "INSERT INTO payments_view VALUES (?,?,?,?,?,?)";
			jdbcTemplate.update(insertStatement, new Object[]{paymentId, accountNumber, customerId, customerName, amount, "PENDING"});
			
			LOG.info("Inserted Payment for payment id:"+paymentId);
		}
		catch(DataAccessException ex){
			LOG.error("DataAccessException for inserting a Payment", ex);
		}catch(Exception ex){
			LOG.error("Exception occured for inserting a Payment", ex);
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handlePaymentCanceledEvent(PaymentCanceledEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		// TODO - Move the database updates to DAO
		LOG.info("Received PaymentCanceledEvent checking datasource:");
		
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			
			String paymentId = event.getPaymentId();
			String updateStatement = "UPDATE payments_view SET status = ? where payment_id = ? ";
			jdbcTemplate.update(updateStatement, new Object[]{"CANCELED", paymentId});
			LOG.info("Updated Payment for payment id:"+paymentId);
		}
		catch(DataAccessException ex){
			LOG.error("DataAccessException for updating a Payment", ex);
		}catch(Exception ex){
			LOG.error("Exception occured for updating a Payment", ex);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handlePaymentScreenedEvent(PaymentScreenedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received PaymentScreenedEvent for Payment Id:"+ event.getPaymentId());
	}
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handlePaymentAccountedEvent(PaymentAccountedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received PaymentAccountedEvent for Payment Id:"+ event.getPaymentId());
	}
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handleAccountUpdatedEvent(AccountUpdatedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received AccountUpdatedEvent for Account Number:"+ event.getAccountNumber());
	}
}
