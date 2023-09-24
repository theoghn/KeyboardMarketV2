package com.tfluke.KBDMarket;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.security.Key;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/kbd")
public class KeyboardController {

    private final KeyboardService service;

    public KeyboardController(KeyboardService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Keyboard>>getKeyboards(){
        //using response entity to manipulate the Status and not get error when returning empty list
        return  new ResponseEntity<List<Keyboard>>(service.getKeyboards(), HttpStatus.OK);
    }
    record NewKeyboard(String layout,
                       String color,
                       Integer price,
                       Integer imagesGroupId,
                       String description){

    }
    @PostMapping
    public ResponseEntity<Keyboard> addKeyboard(@RequestBody Keyboard newKeyboard){
        service.addKeyboard(newKeyboard);
        return new ResponseEntity<Keyboard>(newKeyboard,HttpStatus.OK);

    }
    @PutMapping("{id}")
    public ResponseEntity<String> updateKeyboard(@PathVariable Integer id,@RequestBody Keyboard kbdDetails){
        try {
            service.updateKeyboard(id,kbdDetails);
        }
        catch (ResourceAccessException e){
            return new ResponseEntity<String>("Invalid Id",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>(kbdDetails.toString(),HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKeyboard(@PathVariable Integer id){
        try {
            service.deleteKeyboard(id);
        }
        catch (ResourceAccessException e){
            return new ResponseEntity<String>("Invalid Id",HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("Keyboard with id " + id + " deleted.",HttpStatus.OK);
    }


}
