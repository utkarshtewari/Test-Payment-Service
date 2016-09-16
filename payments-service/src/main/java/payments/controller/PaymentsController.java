package payments.controller;

import javax.servlet.http.HttpServletResponse;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.ConcurrencyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import payments.command.AddPaymentCommand;
import payments.command.CancelPaymentCommand;
import payments.dao.PaymentsDao;
import payments.model.PaymentRequest;
import payments.model.PaymentsCollectionResponse;

@RestController
@RequestMapping(value = "payments-service/accounts/{accountNumber}/")
public class PaymentsController {
	
	private static final Logger LOG = LoggerFactory.getLogger(PaymentsController.class);
	
	@Autowired
    CommandGateway commandGateway;
	
	@Autowired
	PaymentsDao dao;
	
	@RequestMapping(value = "payments/", method = RequestMethod.POST)
	public void executePayment(
    		@PathVariable("accountNumber") String accountNumber, 
    		@RequestBody PaymentRequest payment,
    		HttpServletResponse response) {
    	
		LOG.info("Adding Payment [{}] '{}'", accountNumber, payment);
		
		System.out.println("Received request for payment execution for account:"+accountNumber);
		System.out.println(payment);
		
		try {
			AddPaymentCommand command = new AddPaymentCommand(
					payment.getPaymentId(),
					accountNumber,
					payment.getCustomerId(),
					payment.getCustomerName(),
					payment.getAmount());
			
			commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return;
			
		}catch(CommandExecutionException cex) {
            LOG.warn("Add Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (null != cex.getCause()) {
                LOG.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
                if (cex.getCause() instanceof ConcurrencyException) {
                    LOG.warn("A duplicate payment with the same ID [{}] already exists.", payment.getPaymentId());
                    response.setStatus(HttpServletResponse.SC_CONFLICT);
                }
            }
        }
    	
    }
	
	@RequestMapping(value = "payments/{paymentId}", method = RequestMethod.DELETE)
	public void cancelPayment(
    		@PathVariable("accountNumber") String accountNumber, 
    		@PathVariable("paymentId") String paymentId,
    		HttpServletResponse response) {
    	
		System.out.println("Received request for payment cancel: accountNumber:"+accountNumber+ " payment Id:" +paymentId);
		try {
			CancelPaymentCommand command = new CancelPaymentCommand(
					paymentId,
					accountNumber);
			
			commandGateway.sendAndWait(command);
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
			
		}catch(CommandExecutionException cex) {
            LOG.warn("Cancel Command FAILED with Message: {}", cex.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            if (null != cex.getCause()) {
                LOG.warn("Caused by: {} {}", cex.getCause().getClass().getName(), cex.getCause().getMessage());
            }
        }
    }
	
	@RequestMapping(value="payments/", 
			method = RequestMethod.GET, headers = "Accept=application/json")
	public PaymentsCollectionResponse getPayments(){
		PaymentsCollectionResponse response = new PaymentsCollectionResponse();
		response.setPayments(dao.getAllPayments());
		return response;
	}

}
