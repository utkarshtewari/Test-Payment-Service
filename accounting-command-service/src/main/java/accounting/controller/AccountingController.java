package accounting.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/accounting/accounts")
public class AccountingController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountingController.class);

	@RequestMapping(value = "/{accountNumber}", method = RequestMethod.PUT)
    public void updateBalance(@PathVariable(value = "accountNumber") String accountNumber,
                    HttpServletResponse response) {

        LOG.debug("Updating Account balance [{}] '{}'", accountNumber);
	}

}
