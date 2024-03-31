package com.tfluke.KBDMarket.repository;

import com.tfluke.KBDMarket.model.Switches;
import com.tfluke.KBDMarket.model.keyboard.Keyboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwitchesRepository extends JpaRepository<Switches,Integer> {



}
