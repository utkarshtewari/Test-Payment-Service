package accounting.event;

import org.axonframework.domain.GenericEventMessage;
import org.axonframework.domain.Message;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import payments.event.PaymentAccountedEvent;
import payments.event.PaymentScreenedEvent;

@Component
public class AccountingEventHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountingEventHandler.class);
	
	@Autowired
	EventBus eventBus;
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handlePaymentAddedEvent(PaymentScreenedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received PaymentScreenedEvent in AccountingService");
		
		String paymentId = event.getPaymentId();
		
		PaymentAccountedEvent accountedEvent = new PaymentAccountedEvent(paymentId, "SCREENED");
		
		eventBus.publish(GenericEventMessage.asEventMessage(accountedEvent));
		
		LOG.info("Published PaymentAccountedEvent for payment id:"+paymentId);
	}


}
