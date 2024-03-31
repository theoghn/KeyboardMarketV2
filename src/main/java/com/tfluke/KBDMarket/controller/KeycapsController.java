package com.tfluke.KBDMarket.controller;

import com.tfluke.KBDMarket.model.Keycaps;
import com.tfluke.KBDMarket.service.AuditService;
import com.tfluke.KBDMarket.service.KeycapsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/keycaps")
public class KeycapsController {
    private final KeycapsService keycapsService;
    private final AuditService auditService;

    public KeycapsController(
            KeycapsService keycapsService,
            AuditService auditService) {
        this.keycapsService = keycapsService;
        this.auditService = auditService;
    }

    @PostMapping
    public ResponseEntity<Keycaps> addKeycaps(@RequestBody Keycaps newKeycaps) {
        keycapsService.addKeycaps(newKeycaps);
        auditService.logAction("Keycaps Post");
        return new ResponseEntity<>(newKeycaps, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateKeycaps(@PathVariable Integer id, @RequestBody Keycaps keycaps) {
        try {
            keycapsService.updateKeycaps(id, keycaps);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keycaps Put");
        return new ResponseEntity<>(keycaps.toString(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteKeycaps(@PathVariable Integer id) {
        try {
            keycapsService.deleteKeycaps(id);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keycaps with id " + id + " deleted.");
        return new ResponseEntity<>("Keycaps with id " + id + " deleted.", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Keycaps>> getAllKeycaps() {
        List<Keycaps> allKeycaps = keycapsService.getKeycaps();
        auditService.logAction("Keycaps Get");
        return ResponseEntity.ok(allKeycaps);
    }
}
