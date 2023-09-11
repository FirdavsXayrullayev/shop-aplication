package kv25.shop.controller;

import kv25.shop.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @GetMapping
    public Boolean sendMessage(@RequestParam String email, @RequestParam String message){
        return emailService.sendMessage(email, message);
    }
}
