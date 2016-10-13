package payments.configuration;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class DbConfiguration {
	
	private static final Logger LOG = LoggerFactory.getLogger(DbConfiguration.class);
	
	@Autowired
    @Qualifier("transactionManager")
    protected PlatformTransactionManager txManager;

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void init(){
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("create table if not exists payments_view (payment_id VARCHAR, account_number VARCHAR, customer_id VARCHAR, customer_name VARCHAR, amount FLOAT, status VARCHAR)");
        LOG.info("Created table payments_view");
    }
}
