package com.shopping.cart.management.cart;

import com.shopping.cart.management.product.Product;
import com.shopping.cart.management.tax.TaxSlab;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Cart {

    private final long id;
    private Set<Product> products = new HashSet<>();
    private double totalPrice = 0.0;
    private double salesTaxPrice = 0.0;

    public Cart() {
        id = new Random().nextInt();
        totalPrice = 0.0;
        salesTaxPrice = 0.0;
    }

    public Cart(final Product product) {
        id = new Random().nextInt();
        products.add(product);
        calculatePrice();
    }

    public Cart(final Set<Product> prods) {
        id = new Random().nextInt();
        products = prods;
        calculatePrice();
    }

    public Cart addProduct(final Product product) {
        if (products.isEmpty()) {
            products.add(product);
        }
        else {
            for (Product existingProduct : products) {
                if (existingProduct.getId() == product.getId()) {
                    existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                }
                else {
                    products.add(product);
                }
            }
        }
        calculatePrice();
        return this;
    }

    public double getSalesTaxPrice() {
        return this.salesTaxPrice;
    }

    public double getPrice() {
        return this.totalPrice + this.salesTaxPrice;
    }

    public long getId() {
        return this.id;
    }

    public Set<Product> getProducts() {
        return this.products;
    }

    private void calculatePrice() {
        totalPrice = 0.0;
        salesTaxPrice = 0.0;
        this.products.stream().forEach(product -> {
            totalPrice += product.getPrice() * product.getQuantity();
        });
        salesTaxPrice += TaxSlab.getCartSalesValue(totalPrice, TaxSlab.LocationCode.IND);
    }
}
