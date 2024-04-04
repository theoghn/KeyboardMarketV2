package com.tfluke.KBDMarket.controller;

import com.tfluke.KBDMarket.model.Deskmat;
import com.tfluke.KBDMarket.service.AuditService;
import com.tfluke.KBDMarket.service.DeskmatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/deskmat")
public class DeskmatController {
    private final DeskmatService deskmatService;

    private final AuditService auditService;

    public DeskmatController(
            DeskmatService deskmatService,
            AuditService auditService) {
        this.auditService = auditService;
        this.deskmatService = deskmatService;
    }

    @PostMapping("/admin")
    public ResponseEntity<Deskmat> addDeskmat(@RequestBody Deskmat newDeskmat){
        deskmatService.addDeskmat(newDeskmat);
        auditService.logAction("Deskmat Post");
        return new ResponseEntity<>(newDeskmat, HttpStatus.OK);

    }
    @PutMapping("/admin/{id}")
    public ResponseEntity<String> updateDeskmat(@PathVariable Integer id, @RequestBody Deskmat deskmat){
        try {
            deskmatService.updateDeskmat(id,deskmat);
        }
        catch (ResourceAccessException e){
            return new ResponseEntity<>("Invalid Id",HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Deskmat Put");

        return new ResponseEntity<>(deskmat.toString(),HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteDeskmat(@PathVariable Integer id){
        try {
            deskmatService.deleteDeskmat(id);
        }
        catch (ResourceAccessException e){
            return new ResponseEntity<String>("Invalid Id",HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Deskmat Delete");

        return new ResponseEntity<String>("Deskmat with id " + id + " deleted.",HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Deskmat>> getAllDeskmats() {
        List<Deskmat> allDeskmats = deskmatService.getDeskmats();
        auditService.logAction("Deskmat Get");

        return ResponseEntity.ok(allDeskmats);
    }

}
