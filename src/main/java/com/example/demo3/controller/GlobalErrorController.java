package com.example.demo3.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Map;


@Controller
public class GlobalErrorController implements ErrorController {
    @RequestMapping("/error")
    public ResponseEntity<Map<String, Object>> handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String path = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        HttpStatus status = HttpStatus.resolve(statusCode != null ? statusCode : 500);
        String errorText = status != null ? status.getReasonPhrase() : "Unknown";

        return ResponseEntity.status(statusCode != null ? statusCode : 500)
                .body(Map.of(
                        "timestamp", LocalDateTime.now(),
                        "status", statusCode != null ? statusCode : 500,
                        "error", errorText,
                        "message", message != null ? message : "Unexpected error",
                        "path", path != null ? path : "N/A"
                ));
    }
}
