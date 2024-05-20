package com.tfluke.KBDMarket.controller;


import com.tfluke.KBDMarket.exception.NotEnoughProductsInStockException;
import com.tfluke.KBDMarket.model.Product;
import com.tfluke.KBDMarket.service.AuditService;
import com.tfluke.KBDMarket.service.ProductService;
import com.tfluke.KBDMarket.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/api/shoppingCart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;
    private final AuditService auditService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService, AuditService auditService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.auditService = auditService;

    }

    @GetMapping("/user")
    public ResponseEntity<?> shoppingCart() {

        Map<Product, Integer> productsInCart = shoppingCartService.getProductsInCart();
        Integer total = shoppingCartService.getTotal();
        ShoppingCartResponse response = new ShoppingCartResponse(productsInCart, total);
        auditService.logAction("Shopping cart accessed");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/addProduct/{productId}")
    public ResponseEntity<?> addProductToCart(@PathVariable("productId") Integer productId) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        auditService.logAction("Product "+productId+" added in Cart");
        return shoppingCart();
    }

    @GetMapping("/user/removeProduct/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable("productId") Integer productId) {
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        auditService.logAction("Product "+productId+" removed from Cart");
        return shoppingCart();
    }

    @GetMapping("/user/checkout")
    public ResponseEntity<?> checkout() {
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return ResponseEntity.ok(e.getMessage());
        }
        auditService.logAction("Order Sent");
        return ResponseEntity.ok("Order sent");
    }

    private record ShoppingCartResponse(Map<Product, Integer> products, Integer total) {

        public Map<Integer, Product> getProducts() {
            Map<Integer, Product> productMap = new HashMap<>();
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                productMap.put(entry.getValue(), entry.getKey());
            }
            return productMap;
        }
    }
}
