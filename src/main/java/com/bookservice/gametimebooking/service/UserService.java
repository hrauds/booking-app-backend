package com.bookservice.gametimebooking.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bookservice.gametimebooking.dto.UserDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.UserMapper;
import com.bookservice.gametimebooking.model.User;
import com.bookservice.gametimebooking.repository.UserRepository;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        log.info("User was successfully created");
    }

    public String login(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new UserException("Wrong email provided", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(userDto.getPassword(), user.getPasswordHash())) {
            return generateToken(userDto);
        }
        else {
            log.error("Provided password was wrong");
            throw new UserException("Wrong password provided", HttpStatus.UNAUTHORIZED);
        }
    }

    private String generateToken(UserDto userDto) {
        Algorithm algorithm = Algorithm.HMAC512("not-a-secret-anymore");
        log.info("Generating a token for user");
        return JWT.create()
                .withSubject(userDto.getEmail())
                .withExpiresAt(new Date((new Date()).getTime() + 1000000))
                .withClaim("email", userDto.getEmail())
                .sign(algorithm);
    }

}