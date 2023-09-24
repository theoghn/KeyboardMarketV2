package com.tfluke.KBDMarket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@Controller
@RequestMapping("/api/v1/image")
public class ImagesController {
    private final ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }
    @PostMapping
    public ResponseEntity<String> addImage(@RequestBody Images images){
        imagesService.addImage(images);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<java.util.List<Images>> getImages(){

        return  new ResponseEntity<java.util.List<Images>>(imagesService.getImages(),HttpStatus.OK);

    }

}
