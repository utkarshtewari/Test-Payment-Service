package accounting.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class DbConfiguration {
	
	//private static final Logger LOG = LoggerFactory.getLogger(DbConfiguration.class);
	
	@Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void init(){
    	//JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.execute("create table accounts(account_number VARCHAR, account_type VARCHAR, customer_id VARCHAR, balance FLOAT)");
        //LOG.info("Created table accounts");
    }
}
