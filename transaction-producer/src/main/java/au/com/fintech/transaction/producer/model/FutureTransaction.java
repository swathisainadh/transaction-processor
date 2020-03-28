package au.com.fintech.transaction.producer.model;

public class FutureTransaction {

    private String message;

    public FutureTransaction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public FutureTransaction setMessage(String message) {
        this.message = message;
        return this;
    }
}
