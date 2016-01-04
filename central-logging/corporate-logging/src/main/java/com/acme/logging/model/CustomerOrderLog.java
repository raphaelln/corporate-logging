package com.acme.logging.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customer_order_log")
public class CustomerOrderLog {

    private Integer customerId;

    private Integer productId;

    /**
     * Always will be 1.0 because in this project will use implicit feedback, always will be the product the user x
     * bought
     */
    private final Double rating = 1.0;

    public Integer getCustomerId() {
        return customerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Double getRating() {
        return rating;
    }

    public void setCustomerId(final Integer customerId) {
        this.customerId = customerId;
    }

    public void setProductId(final Integer productId) {
        this.productId = productId;
    }

}
