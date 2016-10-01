package screening.event;

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
import payments.event.PaymentAddedEvent;
import payments.event.PaymentScreenedEvent;

@Component
public class ScreeningEventHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(ScreeningEventHandler.class);
	
	@Autowired
	EventBus eventBus;
	
	@SuppressWarnings("rawtypes")
	@EventHandler
    public void handlePaymentAddedEvent(PaymentAddedEvent event, 
    		Message eventMessage, @Timestamp DateTime moment) {
		
		LOG.info("Received PaymentAddedEvent:"+event);
		
		String paymentId = event.getPaymentId();
		
		//PaymentScreenedEvent screenedEvent = new PaymentScreenedEvent(paymentId, "SCREENED");
		
		PaymentScreenedEvent screenedEvent = new PaymentScreenedEvent(paymentId, 
				event.getAccountNumber(),
				event.getCustomerId(),
				event.getCustomerName(),
				event.getAmount(),"SCREENED");
		
		eventBus.publish(GenericEventMessage.asEventMessage(screenedEvent));
		
		LOG.info("Published PaymentScreenedEvent for payment id:"+paymentId);
	}

}
