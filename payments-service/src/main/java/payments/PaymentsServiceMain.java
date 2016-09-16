package payments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SuppressWarnings("deprecation")
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@EntityScan("payments.model")
public class PaymentsServiceMain {

	public static void main(String[] args) {
        SpringApplication.run(PaymentsServiceMain.class, args);
    }
	
	
}
