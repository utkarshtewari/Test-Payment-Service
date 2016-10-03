package accounting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import accounting.dao.AccountsDao;
import accounting.model.AccountsCollectionResponse;


@RestController
@RequestMapping("accounting-query-service/accounts/")
public class AccountingController {
	
	@Autowired
	AccountsDao dao;
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountingController.class);

	@RequestMapping(method = RequestMethod.GET)
    public AccountsCollectionResponse getAccountDetails() {

        LOG.debug("Retrieving accounts");
        
        AccountsCollectionResponse response = new AccountsCollectionResponse();
		response.setAccounts(dao.getAllAccounts());
		return response;
	}

}
