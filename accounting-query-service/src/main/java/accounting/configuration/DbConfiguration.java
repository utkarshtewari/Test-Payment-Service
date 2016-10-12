package accounting.configuration;

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
    	jdbcTemplate.execute("create table if not exists accounts(account_number VARCHAR2(30), account_type VARCHAR2(12), customer_id VARCHAR2(30), balance FLOAT)");
    	LOG.info("Created table accounts");
    }
}
