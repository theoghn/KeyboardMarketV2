package com.tfluke.KBDMarket;

import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.awt.*;
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
