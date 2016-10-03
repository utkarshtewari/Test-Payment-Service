package accounting.handler;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.domain.Message;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.repository.ConcurrencyException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import payments.event.PaymentScreenedEvent;
import accounting.aggregate.AccountAggregate;
import accounting.command.AddAccountCommand;
import accounting.command.UpdateAccountCommand;

@Component
public class AccountingEventHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountingEventHandler.class);
	
	@Autowired
	EventBus eventBus;
	
	@Autowired
    CommandGateway commandGateway;
	
	@Autowired
	EventSourcingRepository<AccountAggregate> accountEventSourcingRepository;
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handlePaymentScreenedEvent(PaymentScreenedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received PaymentScreenedEvent in Accounting-Command-Service"+event);
//		if(!this.isAccountAvailable(event.getAccountNumber())){
//			this.raiseAddAccountCommand(event);
//		}
		this.raiseAddAccountCommand(event);
		this.raiseUpdateAccountCommand(event);
		
	}
	
//	private boolean isAccountAvailable(String accountNumber) {
//		boolean isAccountAvailable = false;
//		
//		try {
//			AccountAggregate accountNumberAggregate = accountEventSourcingRepository.load(accountNumber);
//			LOG.info("Loaded Account"+accountNumberAggregate);
//			isAccountAvailable = false;
//		}catch(Throwable ex){
//			LOG.warn("Execption dueing Account Availability Check: {}", ex.getMessage());
//        	if (null != ex.getCause()) {
//                LOG.warn("Exception.....Caused by: {} {}", ex.getCause().getClass().getName(), ex.getCause().getMessage());
//            }
//        	isAccountAvailable = false;
//		}
//		return isAccountAvailable;
//	}
	
	private void raiseAddAccountCommand(PaymentScreenedEvent event) {
		
		try {
			AddAccountCommand addAccountCommand = new AddAccountCommand(
					event.getAccountNumber(), event.getCustomerName(), 
					event.getCustomerId(), event.getAmount());
			commandGateway.sendAndWait(addAccountCommand);
			
		}catch(CommandExecutionException cex) {
            LOG.warn("CommandExecutionException Account Add Command FAILED with Message: {}", cex.getMessage());

            if (null != cex.getCause()) {
                LOG.warn("CommandExecutionException Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOG.warn("A duplicate payment with the same Account [{}] already exists.", event.getAccountNumber());
                }
            }
        }
		catch(Throwable ex) {
        	LOG.warn("Execption......Account Add Command FAILED with Message: {}", ex.getMessage());
        	if (null != ex.getCause()) {
                LOG.warn("Exception.....Caused by: {} {}", ex.getCause().getClass().getName(), ex.getCause().getMessage());
            }
        }
	}
	
	private void raiseUpdateAccountCommand(PaymentScreenedEvent event) {
		try {
			UpdateAccountCommand updateAccountCommand = new UpdateAccountCommand(
					event.getAccountNumber(), event.getCustomerName(), 
					event.getCustomerId(), event.getAmount());
			
			commandGateway.sendAndWait(updateAccountCommand);
		}catch(CommandExecutionException cex) {
            LOG.warn("Account Update Command FAILED with Message: {}", cex.getMessage());

            if (null != cex.getCause()) {
                LOG.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
            }
        }
	}

}
