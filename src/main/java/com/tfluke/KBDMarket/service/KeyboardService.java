package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Keyboard;
import com.tfluke.KBDMarket.utils.NullPropertyFinder;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.tfluke.KBDMarket.repository.KeyboardRepository;

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
        if (kbd == null || kbd.getQuantity() == null) {
            throw new IllegalArgumentException("One or more not nullable variables are null.");
        }
        keyboardRepository.save(kbd);
    }
    public Keyboard findKeyboardByID(Integer id){
        return keyboardRepository.findById(id).orElseThrow(()->new ResourceAccessException("Employee not exist with id: " + id));
    }
    public void updateKeyboard(Integer id, Keyboard kbd){
        Keyboard updateKbd = findKeyboardByID(id);
        BeanUtils.copyProperties(kbd, updateKbd, NullPropertyFinder.getNullPropertyNames(kbd));
        keyboardRepository.save(updateKbd);
    }
    public void deleteKeyboard(Integer id){
        Keyboard deleteKbd = findKeyboardByID(id);
        keyboardRepository.delete(deleteKbd);
    }

    public void increaseStock(Integer id,Integer incomingStock){
        Keyboard newKeboard = findKeyboardByID(id);
        newKeboard.setQuantity(newKeboard.getQuantity()+incomingStock);
        keyboardRepository.save(newKeboard);
    }

}
