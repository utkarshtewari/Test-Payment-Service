package screening;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@EntityScan("screening.model")
public class ScreeningServiceMain {

	public static void main(String[] args) {
        SpringApplication.run(ScreeningServiceMain.class, args);
    }
	
	
}
