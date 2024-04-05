package com.tfluke.KBDMarket.service;

import com.tfluke.KBDMarket.model.Deskmat;
import com.tfluke.KBDMarket.model.Keycaps;
import com.tfluke.KBDMarket.repository.DeskmatRepository;
import com.tfluke.KBDMarket.utils.NullPropertyFinder;
import org.springframework.beans.BeanUtils;
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

    public void addDeskmat(Deskmat deskmat){
        deskmatRepository.save(deskmat);
    }

    public Deskmat findDeskmatByID(Integer id){
        return deskmatRepository.findById(id).orElseThrow(()->new ResourceAccessException("Could not find Deskmat with id " + id));
    }
    public void updateDeskmat(Integer id, Deskmat deskmat){
        Deskmat newDeskmat = findDeskmatByID(id);
//        copy the properties that are not null
        BeanUtils.copyProperties(deskmat, newDeskmat, NullPropertyFinder.getNullPropertyNames(deskmat));
        deskmatRepository.save(newDeskmat);
    }
    public void deleteDeskmat(Integer id){
        Deskmat deleteDeskmat = findDeskmatByID(id);
        deskmatRepository.delete(deleteDeskmat);
    }
    public void increaseStock(Integer id,Integer incomingStock){
        Deskmat newDeskmat = findDeskmatByID(id);
        newDeskmat.setQuantity(newDeskmat.getQuantity()+incomingStock);
        deskmatRepository.save(newDeskmat);

    }


}
