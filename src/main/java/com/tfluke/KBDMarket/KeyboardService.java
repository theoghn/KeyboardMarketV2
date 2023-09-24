package com.tfluke.KBDMarket;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.security.Key;
import java.util.List;

@Service
public class KeyboardService {

    private final KeyboardRepository keyboardRepository;

    public KeyboardService(KeyboardRepository repo) {
        this.keyboardRepository = repo;
    }

    public List<Keyboard> getKeyboards(){

        return keyboardRepository.findAll();

    }
    public void addKeyboard(Keyboard kbd){
        keyboardRepository.save(kbd);
    }
    public Keyboard findKeyboardByID(Integer id){
        return keyboardRepository.findById(id).orElseThrow(()->new ResourceAccessException("Employee not exist with id: " + id));
    }
    public void updateKeyboard(Integer id, Keyboard kbd){
        Keyboard updateKbd = findKeyboardByID(id);
        updateKbd.setDescription(kbd.getDescription());
        keyboardRepository.save(updateKbd);
    }
    public void deleteKeyboard(Integer id){
        Keyboard deleteKbd = findKeyboardByID(id);
        keyboardRepository.delete(deleteKbd);
    }

}
