package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Switches;
import com.tfluke.KBDMarket.repository.SwitchesRepository;
import com.tfluke.KBDMarket.utils.NullPropertyFinder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class SwitchesService {
    private final SwitchesRepository switchesRepository;

    @Autowired
    public SwitchesService(SwitchesRepository repo) {
        this.switchesRepository = repo;
    }

    public List<Switches> getSwitches() {
        return switchesRepository.findAll();
    }

    public void addSwitches(Switches switches) {
        switchesRepository.save(switches);
    }

    public Switches findSwitchesById(Integer id) {
        return switchesRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Could not find Switches with id " + id));
    }

    public void updateSwitches(Integer id, Switches switches) {
        Switches newSwitches = findSwitchesById(id);
        BeanUtils.copyProperties(switches, newSwitches, NullPropertyFinder.getNullPropertyNames(switches));
        switchesRepository.save(newSwitches);
    }

    public void deleteSwitches(Integer id) {
        Switches deleteSwitches = findSwitchesById(id);
        switchesRepository.delete(deleteSwitches);
    }
}