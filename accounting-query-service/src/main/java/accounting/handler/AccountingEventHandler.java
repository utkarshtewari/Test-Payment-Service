package accounting.handler;

import javax.sql.DataSource;

import org.axonframework.domain.Message;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		this.updateAccountWithJdbcTemplate(event);
		//this.updateAccountWithDao(event);
	}
	
	private void updateAccountWithJdbcTemplate(AccountUpdatedEvent event){
		String accountNumber = event.getAccountNumber();
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String retrieveStatement = "SELECT balance from accounts where account_number = ? ";
		Double balance = (Double)jdbcTemplate.queryForObject(retrieveStatement, new Object[] { accountNumber }, Double.class);
		
		// update account
		double currentBalance = balance - event.getAmount();
		String updateStatement = "UPDATE accounts SET balance = ? where account_number = ? ";
		jdbcTemplate.update(updateStatement, new Object[]{currentBalance, accountNumber});
		LOG.info("Updated Balance for account:"+accountNumber);
	}
	
//	private void updateAccountWithDao(AccountUpdatedEvent event){
//		Account account = new Account();
//		account.setAccountNumber(event.getAccountNumber());
//		account.setCustomerId(event.getCustomerId());
//		accountsDao.updateAccountBalance(new Account(), event.getAmount());
//	}
	
	@SuppressWarnings("rawtypes")
	@EventHandler
	public void handleAccountAddededEvent(AccountAddedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received AccountAddedEvent in Accounting-Query-Service");
		this.addAccountWithJdbcTemplate(event);
		//this.addAccountWithDao(event);
	}
	
	private void addAccountWithJdbcTemplate(AccountAddedEvent event){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		// insert account
		String accountNumber = event.getAccountNumber();
		String customerId = event.getCustomerId();
		double balance = 10000;
		String insertStatement = "INSERT INTO accounts VALUES (?,?,?,?)";
		jdbcTemplate.update(insertStatement, new Object[]{accountNumber, "SAVINGS", customerId, balance});
		
	}
	
//	private void addAccountWithDao(AccountAddedEvent event){
//		Account account = new Account();
//		account.setAccountNumber(event.getAccountNumber());
//		account.setCustomerId(event.getCustomerId());
//		account.setAccountType("CHECKING");
//		account.setBalance(10000);
//		accountsDao.createAccount(account);
//	}
}
