package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Images;
import com.tfluke.KBDMarket.repository.ImagesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagesService {
    private final ImagesRepository imagesRepository;

    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }
    public void addImage(Images image){
        imagesRepository.save(image);

    }

    public List<Images> getImages() {
        return imagesRepository.findAll();
    }
}
