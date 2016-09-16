package accounting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("deprecation")
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@EntityScan("accounting.model")
public class AccountingServiceMain {
	public static void main(String[] args) {
        SpringApplication.run(AccountingServiceMain.class, args);
    }
}
