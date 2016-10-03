package accounting.dao;

import java.util.List;

import accounting.model.Account;

public interface AccountsDao {
	public List<Account> getAllAccounts();
}
