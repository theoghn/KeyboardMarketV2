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
@RequestMapping("/api/keycaps")
public class KeycapsController {
    private final KeycapsService keycapsService;
    private final AuditService auditService;

    public KeycapsController(
            KeycapsService keycapsService,
            AuditService auditService) {
        this.keycapsService = keycapsService;
        this.auditService = auditService;
    }

    @PostMapping("/admin")
    public ResponseEntity<?> addKeycaps(@RequestBody Keycaps newKeycaps) {
        try {
            keycapsService.addKeycaps(newKeycaps);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
        auditService.logAction("Keycaps Added");
        return new ResponseEntity<>(newKeycaps, HttpStatus.OK);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateKeycaps(@PathVariable Integer id, @RequestBody Keycaps keycaps) {
        try {
            keycapsService.updateKeycaps(id, keycaps);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keycaps "+id+"  Update");
        return new ResponseEntity<>(keycaps, HttpStatus.OK);
    }

    @PutMapping("/admin/{id}/{incomingStock}")
    public ResponseEntity<String> increaseStock(@PathVariable Integer id, @PathVariable Integer incomingStock) {
        try {
            keycapsService.increaseStock(id, incomingStock);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keycaps "+id+"  Stock Increase");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteKeycaps(@PathVariable Integer id) {
        try {
            keycapsService.deleteKeycaps(id);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keycaps with id " + id + " deleted.");
        return new ResponseEntity<>("Keycaps with id " + id + " deleted.", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Keycaps>> getAllKeycaps() {
        List<Keycaps> allKeycaps = keycapsService.getKeycaps();
        auditService.logAction("Keycaps accessed");
        return ResponseEntity.ok(allKeycaps);
    }
}
