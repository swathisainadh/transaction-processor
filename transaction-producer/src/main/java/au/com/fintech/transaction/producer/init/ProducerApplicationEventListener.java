package au.com.fintech.transaction.producer.init;

import au.com.fintech.transaction.producer.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProducerApplicationEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerApplicationEventListener.class);

    private TransactionService service;

    @Autowired
    public ProducerApplicationEventListener(TransactionService service) {
        this.service = service;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        LOGGER.info("Kafka Producer Application has started, triggering transactions refresh and publish messages");
        if (service.readAndSaveTransactions()) {
            service.publishTransactions();
        }
    }
}
