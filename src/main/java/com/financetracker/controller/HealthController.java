package com.financetracker.controller;

import com.financetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/health")
@RequiredArgsConstructor
public class HealthController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "Finance Tracker API is running");

        // Test database connection
        try {
            long userCount = userRepository.count();
            response.put("database", "Connected");
            response.put("userCount", userCount);
        } catch (Exception e) {
            response.put("database", "Error: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }
}
