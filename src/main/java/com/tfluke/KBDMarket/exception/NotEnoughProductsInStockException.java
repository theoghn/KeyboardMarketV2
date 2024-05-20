package com.tfluke.KBDMarket.exception;

import com.tfluke.KBDMarket.model.Product;

public class NotEnoughProductsInStockException extends Exception {

    public NotEnoughProductsInStockException(Product product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getName(), product.getQuantity()));
    }

}