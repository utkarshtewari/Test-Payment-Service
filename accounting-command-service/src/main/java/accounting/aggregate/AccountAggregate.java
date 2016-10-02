package accounting.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import accounting.command.AddAccountCommand;
import accounting.command.UpdateAccountCommand;
import accounts.event.AccountAddedEvent;
import accounts.event.AccountUpdatedEvent;

@SuppressWarnings("rawtypes")
public class AccountAggregate extends AbstractAnnotatedAggregateRoot {
	
	private static final long serialVersionUID = 5993512498306482836L;

	private static final Logger LOG = LoggerFactory.getLogger(AccountAggregate.class);
	
	@AggregateIdentifier
	private String accountNumber;
	private String customerId;
	private String customerName;
	private double balance;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getCustomerId() {
		return customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public double getBalance() {
		return balance;
	}
	
	public AccountAggregate(){}
	
	@CommandHandler
    public AccountAggregate(AddAccountCommand addAccountCommand){
    	LOG.info("Received AddAccountCommand");
    	apply(new AccountAddedEvent( 
    			addAccountCommand.getAccountNumber(), 
    			addAccountCommand.getCustomerId(), 
    			addAccountCommand.getCustomerName(), 
    			addAccountCommand.getBalance()));
    }
	
	@CommandHandler
    public void updateAccount(UpdateAccountCommand accountUpdateCommand) {
		LOG.info("Received AccountUpdateCommand");
		
		apply(new AccountUpdatedEvent( 
				accountUpdateCommand.getAccountNumber(),
				accountUpdateCommand.getCustomerId(),
				accountUpdateCommand.getCustomerName(),
				accountUpdateCommand.getAmount()));
    }
	
	@EventSourcingHandler
    private void handleAccountAdded(AccountAddedEvent event) {
		LOG.info("EventSourcingHandler AccountAddedEvent:"+ event);
		
		this.accountNumber = event.getAccountNumber();
		this.customerId = event.getCustomerId();
		this.customerName = event.getCustomerName();
		this.balance = event.getBalance();
    }
	
	@EventSourcingHandler
    private void handleAccountUpdated(AccountUpdatedEvent event) {
		LOG.info("EventSourcingHandler AccountUpdatedEvent:"+ event);
		this.accountNumber = event.getAccountNumber();
		this.customerId = event.getCustomerId();
		this.customerName = event.getCustomerName();
		this.balance = balance - event.getAmount();
    }
}
