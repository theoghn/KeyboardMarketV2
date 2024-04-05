package com.tfluke.KBDMarket;

import com.tfluke.KBDMarket.model.ShoppingCart;
import com.tfluke.KBDMarket.repository.ShoppingCartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//i want to have only one cart for the time beeing
@Component
public class ShoppingCartInitializer implements ApplicationRunner {

    private final ShoppingCartRepository shoppingCartRepository;

    @Autowired
    public ShoppingCartInitializer(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        // Check if the shopping cart exists
        if (shoppingCartRepository.count() == 0) {
            // Create a new shopping cart
            ShoppingCart shoppingCart = new ShoppingCart();
            // Set any necessary properties
            // ...
            // Save the shopping cart
            shoppingCartRepository.save(shoppingCart);
        }
    }
}
