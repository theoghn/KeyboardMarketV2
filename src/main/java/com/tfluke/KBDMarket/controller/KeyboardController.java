package com.tfluke.KBDMarket.controller;

import com.tfluke.KBDMarket.model.Keyboard;
import com.tfluke.KBDMarket.service.AuditService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import com.tfluke.KBDMarket.service.KeyboardService;

import java.util.List;

@RestController
@RequestMapping("/api/kbd")
public class KeyboardController {

    private final KeyboardService keyboardService;

    private final AuditService auditService;

    public KeyboardController(
            KeyboardService keyboardService,
            AuditService auditService) {
        this.auditService = auditService;
        this.keyboardService = keyboardService;

    }

    @PostMapping("/admin")
    public ResponseEntity<?> addKeyboard(@RequestBody Keyboard newKeyboard) {
        try {
            keyboardService.addKeyboard(newKeyboard);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.OK);
        }
        auditService.logAction("Keyboard Added");
        return new ResponseEntity<Keyboard>(newKeyboard, HttpStatus.OK);

    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<?> updateKeyboard(@PathVariable Integer id, @RequestBody Keyboard kbdDetails) {
        try {
            keyboardService.updateKeyboard(id, kbdDetails);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keyboard "+id+" Update");

        return new ResponseEntity<>(kbdDetails, HttpStatus.OK);
    }

    @PutMapping("/admin/{id}/{incomingStock}")
    public ResponseEntity<String> increaseStock(@PathVariable Integer id, @PathVariable Integer incomingStock) {
        try {
            keyboardService.increaseStock(id, incomingStock);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keyboard "+id+" Stock Increase");

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<String> deleteKeyboard(@PathVariable Integer id) {
        try {
            keyboardService.deleteKeyboard(id);
        } catch (ResourceAccessException e) {
            return new ResponseEntity<String>("Invalid Id", HttpStatus.NO_CONTENT);
        }
        auditService.logAction("Keyboard "+id+" Deleted");

        return new ResponseEntity<String>("Keyboard with id " + id + " deleted.", HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Keyboard>> getAllKeyboards() {

        List<Keyboard> allKeyboards = keyboardService.getKeyboards();
        auditService.logAction("Keyboard accessed");

        return ResponseEntity.ok(allKeyboards);
    }


}
