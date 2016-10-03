package accounting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountsCollectionResponse implements Serializable {
	
	private static final long serialVersionUID = 4912618706770414089L;
	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public void addAccount(Account account) {
		if (this.accounts == null) {
			this.accounts = new ArrayList<Account>();
		}
		this.accounts.add(account);
	}

}
