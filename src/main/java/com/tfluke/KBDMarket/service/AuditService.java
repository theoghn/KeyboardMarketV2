package com.tfluke.KBDMarket.service;

import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AuditService {
    private static final Logger logger = LoggerFactory.getLogger(AuditService.class);
    private static final String CSV_FILE_PATH = "audit_log.csv";
    private static final String CSV_HEADER = "action_name,timestamp";
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logAction(String actionName) {
        try (FileWriter writer = new FileWriter(CSV_FILE_PATH, true)) {
            String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMATTER);
            writer.append(actionName).append(",").append(timestamp).append("\n");
        } catch (IOException e) {
            logger.error("Failed to log action '{}'", actionName, e);
        }
    }

}
