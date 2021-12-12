package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.exception.UserNameException;
import com.example.demo.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Mono<User> save(User user){
        return userRepository.existsByUsername(user.getUsername())
                .flatMap(
                        exits->{
                            if (exits){
                                return Mono.error(
                                        new UserNameException(
                                                String.format("User with [%s] name already exists", user.getUsername())
                                        )
                                );
                            }
                            return userRepository.save(user);
                        }
                );
    }



}
