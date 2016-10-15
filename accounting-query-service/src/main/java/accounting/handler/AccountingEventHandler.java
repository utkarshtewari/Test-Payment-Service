package accounting.handler;

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

import accounting.dao.AccountsDao;
import accounts.event.AccountAddedEvent;
import accounts.event.AccountUpdatedEvent;

@Component
public class AccountingEventHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountingEventHandler.class);
	
	@Autowired
    DataSource dataSource;
	
	@Autowired
	AccountsDao accountsDao;
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handleAccountUpdatedEvent(AccountUpdatedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received AccountUpdatedEvent in Accounting-Query-Service");
		
		try {
			double accountBalance = this.getAccountBalance(event);
			this.updateAccountForPayment(event, accountBalance);
		}
		catch(DataAccessException ex){
			LOG.error("DataAccessException occured for update Account", ex);
		}catch(Exception ex){
			LOG.error("Exception occured for update Account", ex);
		}
	}
	
	private void updateAccountForPayment(AccountUpdatedEvent event, double accountBalance) throws DataAccessException {
		String accountNumber = event.getAccountNumber();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		double currentBalance = accountBalance - event.getAmount();
		String updateStatement = "UPDATE accounts SET balance = ? where account_number = ? ";
		jdbcTemplate.update(updateStatement, new Object[]{currentBalance, accountNumber});
		LOG.info("Updated Balance for account:"+accountNumber);
	}
	
	private double getAccountBalance(AccountUpdatedEvent event) throws DataAccessException {
		String accountNumber = event.getAccountNumber();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String retrieveStatement = "SELECT balance from accounts where account_number = ? ";
		Double balance = (Double)jdbcTemplate.queryForObject(retrieveStatement, new Object[] { accountNumber }, Double.class);
		return balance.doubleValue();
	}
	
	@SuppressWarnings("rawtypes")
	@EventHandler
	public void handleAccountAddededEvent(AccountAddedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received AccountAddedEvent in Accounting-Query-Service");
		try {
			this.addAsNewAccount(event);
		}
		catch(DataAccessException ex){
			LOG.error("DataAccessException occured for adding Account", ex);
		}
		catch(Exception ex){
			LOG.error("Exception occured for adding Account", ex);
		}
	}
	
	private void addAsNewAccount(AccountAddedEvent event) throws DataAccessException {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String accountNumber = event.getAccountNumber();
		String customerId = event.getCustomerId();
		double balance = 10000;
		String insertStatement = "INSERT INTO accounts VALUES (?,?,?,?)";
		jdbcTemplate.update(insertStatement, new Object[]{accountNumber, "SAVINGS", customerId, balance});
	}
}
