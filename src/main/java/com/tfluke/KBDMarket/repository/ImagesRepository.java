package com.tfluke.KBDMarket.repository;

import com.tfluke.KBDMarket.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images,Integer>{

}
