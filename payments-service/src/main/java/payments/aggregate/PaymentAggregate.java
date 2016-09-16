package payments.aggregate;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import payments.command.AddPaymentCommand;
import payments.command.CancelPaymentCommand;
import payments.event.PaymentAddedEvent;
import payments.event.PaymentCanceledEvent;
import payments.event.PaymentExecutedEvent;


@SuppressWarnings("rawtypes")
public class PaymentAggregate extends AbstractAnnotatedAggregateRoot {
	
	private static final Logger LOG = LoggerFactory.getLogger(PaymentAggregate.class);
		
	private static final long serialVersionUID = 6060844231564963407L;
	
	@AggregateIdentifier
    private String paymentId;
	private String customerId;
	private String customerName;
	private double amount;
	private String accountNumber;
	private String status;
	
	public PaymentAggregate(){}
		
    public String getPaymentId() {
        return paymentId;
    }
	public String getCustomerName() {
		return customerName;
	}
	public String getCustomerId() {
		return customerId;
	}
	public double getAmount() {
		return amount;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public String getStatus() {
		return status;
	}
	
    @CommandHandler
    public PaymentAggregate(AddPaymentCommand addPaymentCommand){
    	LOG.info("Received AddPaymentCommand");
    	
    	apply(new PaymentAddedEvent(addPaymentCommand.getPaymentId(), 
    			addPaymentCommand.getAccountNumber(), 
    			addPaymentCommand.getCustomerId(), 
    			addPaymentCommand.getCustomerName(), 
    			addPaymentCommand.getAmount()));
    }
    
    @CommandHandler
    public void cancelPayment(CancelPaymentCommand cancelPaymentCommand) {
		apply(new PaymentCanceledEvent(cancelPaymentCommand.getPaymentId(), cancelPaymentCommand.getAccountNumber()));
    }
	
	@EventSourcingHandler
    private void handlePaymentAdded(PaymentAddedEvent event) {
		LOG.info("EventSourcingHandler PaymentAddedEvent:"+ event);
		
		this.paymentId = event.getPaymentId();
		this.accountNumber = event.getAccountNumber();
		this.customerId = event.getCustomerId();
		this.customerName = event.getCustomerName();
		this.amount = event.getAmount();
        this.status = "PENDING";
    }
	
	@EventSourcingHandler
    private void handlePaymentCancel(PaymentCanceledEvent event) {
		LOG.info("EventSourcingHandler PaymentCanceledEvent:"+ event);
		this.paymentId = event.getPaymentId();
		this.accountNumber = event.getAccountNumber();
        this.status = "CANCELED";
    }
	
	@EventSourcingHandler
    private void handlePaymentExecuted(PaymentExecutedEvent event) {
		LOG.info("EventSourcingHandler PaymentCanceledEvent:"+ event);
		this.paymentId = event.getPaymentId();
		this.accountNumber = event.getAccountNumber();
        this.status = "EXECUTED";
    }
}
