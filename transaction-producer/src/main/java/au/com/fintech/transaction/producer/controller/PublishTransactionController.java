package au.com.fintech.transaction.producer.controller;

import au.com.fintech.transaction.producer.model.FutureTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("publish/transaction")
public class PublishTransactionController {

    private KafkaTemplate<String, FutureTransaction> kafkaTemplate;
    private String topicName;

    @Autowired
    public PublishTransactionController(final KafkaTemplate<String, FutureTransaction> kafkaTemplate,
                                        @Value("${publish.transaction.kafka.topic.name}") final String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @GetMapping
    public String sendTransaction() {

        kafkaTemplate.send(topicName, new FutureTransaction("initial test value"));

        return "Published successfully";
    }
}

