package com.tfluke.KBDMarket.repository;

import com.tfluke.KBDMarket.model.Deskmat;
import com.tfluke.KBDMarket.model.Keyboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeskmatRepository extends JpaRepository<Deskmat,Integer> {



}
