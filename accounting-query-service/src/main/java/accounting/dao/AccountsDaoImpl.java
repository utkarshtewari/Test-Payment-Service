package accounting.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import accounting.model.Account;

@Repository
public class AccountsDaoImpl extends JdbcDaoSupport implements AccountsDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountsDaoImpl.class);
	
	@Autowired
    DataSource dataSource;
	
	//@Autowired 
	//private TransactionTemplate transactionTemplate;
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Account> getAllAccounts() {
		LOG.info("Retrieving Accounts");
		
		List<Account> accounts = new ArrayList<Account>();
				
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String accountsQuery = "Select account_number, account_type, customer_id, balance from accounts";
		
		jdbcTemplate.query(accountsQuery, 
				(rs, rowNum) -> new Account(rs.getString("account_number"),
				rs.getString("account_type"),
				rs.getString("customer_id"),
				rs.getDouble("balance")))
			.forEach(account -> accounts.add(account));
		
		LOG.info("Retrieved account:"+accounts.size());
		return accounts;
	}

	@Override
	public int updateAccountBalance(Account account, Double paymentAmount) {
		LOG.info("Received Account updateAccountBalance");
		String accountNumber = account.getAccountNumber();
//		
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		String retrieveStatement = "SELECT balance from accounts where account_number = ? ";
//		Double balance = (Double)jdbcTemplate.queryForObject(retrieveStatement, new Object[] { accountNumber }, Double.class);
//		
//		// update account
//		double currentBalance = balance - paymentAmount.doubleValue();
//		String updateStatement = "UPDATE accounts SET balance = ? where account_number = ? ";
//		jdbcTemplate.update(updateStatement, new Object[]{currentBalance, accountNumber});
//		LOG.info("Updated Balance for account:"+accountNumber);
		
		Account accountToBeUpdated = em.getReference(Account.class, accountNumber);
		double currentBalance = accountToBeUpdated.getBalance() - paymentAmount.doubleValue();
		accountToBeUpdated.setBalance(currentBalance);
		
		//em.merge(account);
		LOG.info("Updated Balance for account:"+accountNumber);
		return 1;
	}
	
	@Override
	public int createAccount(Account account) {
		LOG.info("Create Account in Dao");
		
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		
//		// insert account
//		String accountNumber = account.getAccountNumber();
//		String customerId = account.getCustomerId();
//		double balance = 10000;
//		String insertStatement = "INSERT INTO accounts VALUES (?,?,?,?)";
//		jdbcTemplate.update(insertStatement, new Object[]{accountNumber, "SAVINGS", customerId, balance});
		
		em.persist(account);
		LOG.info("Created Account");
		return 1;
	}
	
	
}
