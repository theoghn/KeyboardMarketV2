package com.tfluke.KBDMarket.repository;

import com.tfluke.KBDMarket.model.Keycaps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KeycapsRepository extends JpaRepository<Keycaps,Integer> {



}
