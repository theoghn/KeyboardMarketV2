package com.tfluke.KBDMarket.controller;

import com.tfluke.KBDMarket.model.Switches;
import com.tfluke.KBDMarket.service.AuditService;
import com.tfluke.KBDMarket.service.SwitchesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/switches")
public class SwitchesController {
    private final SwitchesService switchesService;
    private final AuditService auditService;

    public SwitchesController(
            SwitchesService switchesService,
            AuditService auditService) {
        this.switchesService = switchesService;
        this.auditService = auditService;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> addSwitches(@RequestBody Switches newSwitches) {
        try {
            switchesService.addSwitches(newSwitches);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
        auditService.logAction("Switches Added");
        return new ResponseEntity<>(newSwitches, HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateSwitches(@PathVariable Integer id, @RequestBody Switches switches) {
        try {
            switchesService.updateSwitches(id, switches);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Switches "+id+" Update");
        return ResponseEntity.ok(switches);
    }

    @PutMapping("/admin/{id}/{incomingStock}")
    public ResponseEntity<String> increaseStock(@PathVariable Integer id, @PathVariable Integer incomingStock) {
        try {
            switchesService.increaseStock(id, incomingStock);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Switches "+id+" Stock Increase");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteSwitches(@PathVariable Integer id) {
        try {
            switchesService.deleteSwitches(id);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Switches with id " + id + " deleted.");
        return new ResponseEntity<>("Switches with id " + id + " deleted.", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Switches>> getAllSwitches() {
        List<Switches> allSwitches = switchesService.getSwitches();
        auditService.logAction("Switches accessed");
        return ResponseEntity.ok(allSwitches);
    }
}