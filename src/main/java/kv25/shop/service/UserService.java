package kv25.shop.service;

import kv25.shop.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    ResponseEntity<UserDto> addNewUser(String fullName, String username, String email);

    ResponseEntity<UserDto> getUserById(Integer id);

    ResponseEntity<List<UserDto>> getAllUser();

    Boolean verifyEmail(String email, String password);

    ResponseEntity<String> deleteUser(Integer id);
}
