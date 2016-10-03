package accounting.dao;

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

import accounting.model.Account;

@Repository
public class AccountsDaoImpl extends JdbcDaoSupport implements AccountsDao {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountsDaoImpl.class);
	
	@Autowired
    DataSource dataSource;
	
	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Account> getAllAccounts() {
		LOG.info("Retrieving Accounts");
		
		List<Account> accounts = new ArrayList<Account>();
				
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//		String accountsQuery = "Select accountNumber, accountType, customerId, "
//				+ "balance from accounts";
		
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
	
	
}
