package com.example.comunio.controller;

import com.example.comunio.model.dto.UserDto;
import com.example.comunio.model.request.CreateUserRequest;
import com.example.comunio.model.request.UpdateUserRequest;
import com.example.comunio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUsers(@RequestBody List<CreateUserRequest> createUserRequests) {
        userService.createUsers(createUserRequests);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.findUserDtos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        return userService.updateUser(id, updateUserRequest)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
