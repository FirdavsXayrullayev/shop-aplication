package kv25.shop.service.impl;

import kv25.shop.dto.UserDto;
import kv25.shop.model.Users;
import kv25.shop.repository.UserRepository;
import kv25.shop.service.EmailService;
import kv25.shop.service.UserService;
import kv25.shop.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final EmailService emailService;

    @Override
    public ResponseEntity<UserDto> addNewUser(String fullName, String username, String email) {
        if (userRepository.findByUsername(username).isPresent()) {
            try {
                throw new Exception("has username already been taken" + username);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (userRepository.findByEmail(email).isPresent()) {
            try {
                throw new Exception("has email already been taken: " + email);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        String code = String.valueOf(new Random().nextInt(1000, 10000));
        if (!emailService.sendMessage(email, code)) {
            try {
                throw new Exception("Error send code with email");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Users users = new Users(null, fullName, username, email, code);
        return ResponseEntity.ok(userMapper.toDto(userRepository.save(users)));
    }

    @Override
    public ResponseEntity<UserDto> getUserById(Integer id) {
        return ResponseEntity.ok(userMapper.toDto(userRepository.findById(id).orElse(new Users())));
    }

    @Override
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(userMapper::toDto).toList());
    }

    @Override
    public Boolean verifyEmail(String email, String password) {
        Users users;
        try {
            users = userRepository.findByEmail(email).orElseThrow(() -> new Exception("User not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return users.getPassword().equals(password);
    }

    @Override
    public ResponseEntity<String> deleteUser(Integer id) {
        return userRepository.findById(id).map(users -> {
            userRepository.deleteById(id);
            return ResponseEntity.ok("OK");
        }).orElse(
                ResponseEntity.ok("Not found")
        );
    }
}
