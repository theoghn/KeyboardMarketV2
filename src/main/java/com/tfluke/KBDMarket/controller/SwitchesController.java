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

    @PostMapping
    public ResponseEntity<Switches> addSwitches(@RequestBody Switches newSwitches) {
        switchesService.addSwitches(newSwitches);
        auditService.logAction("Switches Post");
        return new ResponseEntity<>(newSwitches, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateSwitches(@PathVariable Integer id, @RequestBody Switches switches) {
        try {
            switchesService.updateSwitches(id, switches);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Switches Put");
        return new ResponseEntity<>(switches.toString(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
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
        auditService.logAction("Switches Get");
        return ResponseEntity.ok(allSwitches);
    }
}