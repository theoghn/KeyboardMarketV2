package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Keycaps;
import com.tfluke.KBDMarket.repository.KeycapsRepository;
import com.tfluke.KBDMarket.utils.NullPropertyFinder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import java.util.List;

@Service
public class KeycapsService {
    private final KeycapsRepository keycapsRepository;

    @Autowired
    public KeycapsService(KeycapsRepository repo) {
        this.keycapsRepository = repo;
    }

    public List<Keycaps> getKeycaps() {
        return keycapsRepository.findAll();
    }

    public void addKeycaps(Keycaps keycaps) {
        keycapsRepository.save(keycaps);
    }

    public Keycaps findKeycapsById(Integer id) {
        return keycapsRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Could not find Keycaps with id " + id));
    }

    public void updateKeycaps(Integer id, Keycaps keycaps) {
        Keycaps newKeycaps = findKeycapsById(id);
        BeanUtils.copyProperties(keycaps, newKeycaps, NullPropertyFinder.getNullPropertyNames(keycaps));
        keycapsRepository.save(newKeycaps);
    }

    public void deleteKeycaps(Integer id) {
        Keycaps deleteKeycaps = findKeycapsById(id);
        keycapsRepository.delete(deleteKeycaps);
    }
}
