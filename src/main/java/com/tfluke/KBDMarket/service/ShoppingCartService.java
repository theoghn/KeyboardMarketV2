package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Keycaps;
import com.tfluke.KBDMarket.model.ShoppingCart;
import com.tfluke.KBDMarket.repository.KeycapsRepository;
import com.tfluke.KBDMarket.repository.ShoppingCartRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

import static com.tfluke.KBDMarket.utils.NullPropertyFinder.getNullPropertyNames;

// not fully implemented

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final DeskmatService deskmatService;
    private final KeycapsService keycapsService;
    private final SwitchesService switchesService;
    private final KeyboardService keyboardService;
    @Autowired
    public ShoppingCartService(
            ShoppingCartRepository shoppingCartRepository,
            DeskmatService deskmatService,
            KeycapsService keycapsService,
            SwitchesService switchesService,
            KeyboardService keyboardService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.deskmatService = deskmatService;
        this.keycapsService = keycapsService;
        this.switchesService = switchesService;
        this.keyboardService = keyboardService;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public void addShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart findShoppingCartById(Integer id) {
        return shoppingCartRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Could not find ShoppingCart with id " + id));
    }

    public void updateShoppingCart(Integer id, ShoppingCart shoppingCart) {
        ShoppingCart existingShoppingCart = findShoppingCartById(id);
        BeanUtils.copyProperties(shoppingCart, existingShoppingCart, getNullPropertyNames(shoppingCart));
        shoppingCartRepository.save(existingShoppingCart);
    }
    public void addDeskmatToCart(Integer deskmatId){

    }

    public void deleteShoppingCart(Integer id) {
        ShoppingCart shoppingCartToDelete = findShoppingCartById(id);
        shoppingCartRepository.delete(shoppingCartToDelete);
    }

}
