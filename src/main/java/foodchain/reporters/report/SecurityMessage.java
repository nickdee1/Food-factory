package foodchain.reporters.report;

import foodchain.products.Product;

/**
 * Coverage class for security report.
 */
public class SecurityMessage {

    /**
     * The product of security message.
     */
    public Product product;

    /**
     * The sender of security message.
     */
    public String sender;

    /**
     * The receiver of security message.
     */
    public String receiver;

    /**
     * The timestamp of security message.
     */
    public String timestamp;

    /**
     * The comment of security message.
     */
    public String message;
}
