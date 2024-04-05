package com.tfluke.KBDMarket.repository;

import com.tfluke.KBDMarket.model.Keycaps;
import com.tfluke.KBDMarket.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Integer> {



}
