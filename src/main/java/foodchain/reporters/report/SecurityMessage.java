package foodchain.reporters.report;

import foodchain.products.Product;

/**
 * Coverage class for security report.
 */
public class SecurityMessage {

    /**
     * The product of security message.
     */
    private Product product;

    /**
     * The sender of security message.
     */
    private String sender;

    /**
     * The receiver of security message.
     */
    private String receiver;

    /**
     * The timestamp of security message.
     */
    private String timestamp;

    /**
     * The comment of security message.
     */
    private String message;


    /**
     * Constructor for message coverage class.
     * @param product the product of security message.
     * @param sender the sender of security message.
     * @param receiver the receiver of security message.
     * @param timestamp the timestamp of security message.
     * @param message the comment of security message.
     */
    public SecurityMessage(Product product, String sender, String receiver, String timestamp, String message) {
        this.product = product;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
        this.message = message;
    }

    /**
     * Get the product of security message.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Get the sender of security message.
     */
    public String getSender() {
        return sender;
    }

    /**
     * Get the receiver of security message.
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Get the timestamp of security message.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Get the comment of security message.
     */
    public String getMessage() {
        return message;
    }
}
