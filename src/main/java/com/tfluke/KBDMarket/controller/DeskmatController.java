package com.tfluke.KBDMarket.controller;

import com.tfluke.KBDMarket.model.Deskmat;
import com.tfluke.KBDMarket.service.AuditService;
import com.tfluke.KBDMarket.service.DeskmatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addDeskmat(@RequestBody Deskmat newDeskmat) {
        try {
            deskmatService.addDeskmat(newDeskmat);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
        auditService.logAction("Deskmat Added");
        return new ResponseEntity<>(newDeskmat, HttpStatus.OK);

    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateDeskmat(@PathVariable Integer id, @RequestBody Deskmat deskmat) {
        try {
            deskmatService.updateDeskmat(id, deskmat);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Deskmat "+id+" Update");

        return new ResponseEntity<>(deskmat, HttpStatus.OK);
    }

    @PutMapping("/admin/{id}/{incomingStock}")
    public ResponseEntity<String> increaseStock(@PathVariable Integer id, @PathVariable Integer incomingStock) {
        try {
            deskmatService.increaseStock(id, incomingStock);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Deskmat "+id+" Stock Increase");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteDeskmat(@PathVariable Integer id) {
        try {
            deskmatService.deleteDeskmat(id);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<String>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Deskmat "+id+" Deleted");

        return new ResponseEntity<String>("Deskmat with id " + id + " deleted.", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Deskmat>> getAllDeskmats() {
        List<Deskmat> allDeskmats = deskmatService.getDeskmats();
        auditService.logAction("Deskmat accessed");

        return ResponseEntity.ok(allDeskmats);
    }

}
