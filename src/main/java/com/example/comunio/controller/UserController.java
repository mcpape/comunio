package com.example.comunio.controller;

import com.example.comunio.domain.calculator.BalanceCalculator;
import com.example.comunio.domain.calculator.BalanceResult;
import com.example.comunio.model.CreateUserRequest;
import com.example.comunio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BalanceCalculator balanceCalculator;

    @PostMapping
    public void createUsers(@RequestBody List<CreateUserRequest> createUserRequests) {
        userService.createUsers(createUserRequests);
    }

    @GetMapping
    public List<BalanceResult> getBalance() {
        return balanceCalculator.calculate();
    }

}
