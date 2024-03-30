package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Deskmat;
import com.tfluke.KBDMarket.repository.DeskmatRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@Service
public class DeskmatService {
    private final DeskmatRepository deskmatRepository;

    public DeskmatService(DeskmatRepository repo) {
        this.deskmatRepository = repo;
    }

    public List<Deskmat> getDeskmats(){
        return deskmatRepository.findAll();
    }

    public void addDeskmat(Deskmat kbd){
        deskmatRepository.save(kbd);
    }

    public Deskmat findDeskmatByID(Integer id){
        return deskmatRepository.findById(id).orElseThrow(()->new ResourceAccessException("Could not find Deskmat with id " + id));
    }
    public void updateDeskmat(Integer id, Deskmat kbd){
        Deskmat newDeskmat = findDeskmatByID(id);
        newDeskmat.setDescription(kbd.getDescription());
        deskmatRepository.save(newDeskmat);
    }
    public void deleteDeskmat(Integer id){
        Deskmat deleteDeskmat = findDeskmatByID(id);
        deskmatRepository.delete(deleteDeskmat);
    }
}
