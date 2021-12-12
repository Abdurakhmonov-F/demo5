package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/save")
    public Mono save(@RequestBody User user){

        return Mono.just(user)

                .doOnSuccess(user1 -> user.setId(UUID.randomUUID().toString()))
                .doOnSuccess(user1 -> user.setPassword(passwordEncoder.encode(user.getPassword())))

                .flatMap(userService::save);
    }
}
