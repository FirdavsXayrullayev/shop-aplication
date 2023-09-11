package kv25.shop.service;

import org.springframework.http.ResponseEntity;

public interface EmailService {
    Boolean sendMessage(String email, String message);
}
