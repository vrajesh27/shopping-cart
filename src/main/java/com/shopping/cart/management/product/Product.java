package com.shopping.cart.management.product;

import com.shopping.cart.management.AbstractExpressionBuilder;

public class Product {

    private final long id;
    private final String display;
    private final double price;
    private long quantity;

    private Product(final Builder builder) {
        assert builder != null : "builder:null";

        id = builder.id;
        display = builder.display;
        price = builder.price;
        quantity = builder.quantity;
    }

    public long getId() {
        return this.id;
    }

    public String getDisplay() {
        return this.display;
    }

    public double getPrice() {
        return this.price;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long newQuantity) {
        this.quantity = newQuantity;
    }

    public static final class  Builder extends AbstractExpressionBuilder {
        private long id;
        private String display;
        private double price;
        private long quantity;

        public static Builder create() {
            return new Builder();
        }

        private Builder(){
            id = 0;
            display = "";
            price = 0.0;
            quantity = 0;
        }

        public Builder withId(final long id) {
            verifyBuildingState();
            this.id = id;
            return this;
        }

        public Builder withDisplay(final String display) {
            verifyBuildingState();
            this.display = display;
            return this;
        }

        public Builder withPrice(final double price) {
            verifyBuildingState();
            this.price = price;
            return this;
        }

        public Builder withQuantity(final long quantity) {
            verifyBuildingState();
            this.quantity = quantity;
            return this;
        }

        public Product build() {
            setBuildingComplete();
            return new Product(this);
        }
    }
}
