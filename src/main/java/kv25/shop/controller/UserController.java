package kv25.shop.controller;

import kv25.shop.dto.UserDto;
import kv25.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/getUserById")
    public ResponseEntity<UserDto> getUserById(@RequestParam Integer id){
        return userService.getUserById(id);
    }
    @PostMapping("/addNewUser")
    public ResponseEntity<UserDto> addNewUser(@RequestParam String fullName,@RequestParam String username, @RequestParam String email){
        return userService.addNewUser(fullName, username, email);
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUser(){
        return userService.getAllUser();
    }
    @GetMapping("/verifyEmail")
    public Boolean verifyEmail(@RequestParam String email, @RequestParam String password){
        return userService.verifyEmail(email, password);
    }
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam Integer id){
        return userService.deleteUser(id);
    }
}
