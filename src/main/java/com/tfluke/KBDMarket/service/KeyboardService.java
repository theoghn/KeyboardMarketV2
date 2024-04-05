package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Keycaps;
import com.tfluke.KBDMarket.model.keyboard.Keyboard;
import com.tfluke.KBDMarket.model.keyboard.KeyboardFilters;
import com.tfluke.KBDMarket.model.keyboard.KeyboardPage;
import com.tfluke.KBDMarket.repository.KeyboardCriteriaRepository;
import com.tfluke.KBDMarket.utils.NullPropertyFinder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import com.tfluke.KBDMarket.repository.KeyboardRepository;

import java.util.List;

@Service
public class KeyboardService {

    private final KeyboardRepository keyboardRepository;
    private final KeyboardCriteriaRepository keyboardCriteriaRepository;

    public KeyboardService(KeyboardRepository repo,KeyboardCriteriaRepository repo2) {
        this.keyboardCriteriaRepository = repo2;
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
//        updateKbd.setDescription(kbd.getDescription());
        BeanUtils.copyProperties(kbd, updateKbd, NullPropertyFinder.getNullPropertyNames(kbd));
        keyboardRepository.save(updateKbd);
    }
    public void deleteKeyboard(Integer id){
        Keyboard deleteKbd = findKeyboardByID(id);
        keyboardRepository.delete(deleteKbd);
    }
    public Page<Keyboard> getAllKeyboardsByFilter(KeyboardFilters keyboardFilters,
                                        KeyboardPage keyboardPage){
       return keyboardCriteriaRepository.getAllWithFilters(keyboardFilters,keyboardPage);
    }

    public void increaseStock(Integer id,Integer incomingStock){
        Keyboard newKeboard = findKeyboardByID(id);
        newKeboard.setQuantity(newKeboard.getQuantity()+incomingStock);
        keyboardRepository.save(newKeboard);
    }

}
