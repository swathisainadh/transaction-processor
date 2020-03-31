package au.com.fintech.transaction.producer.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

import static au.com.fintech.transaction.producer.model.MessageStatus.NEW;

@Entity
public class ClientTransaction implements Serializable {

    private static final long serialVersionUID = -2343243243242432341L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String message;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageStatus status = NEW;

    @Version
    private Date updatedAt;

    public ClientTransaction() {}

    public ClientTransaction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
