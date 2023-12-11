package com.bookservice.gametimebooking.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bookservice.gametimebooking.dto.UserDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.UserMapper;
import com.bookservice.gametimebooking.model.User;
import com.bookservice.gametimebooking.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPasswordHash(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
    }

    public String login(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new UserException("Wrong email provided", HttpStatus.NOT_FOUND));
        if (passwordEncoder.matches(userDto.getPassword(), user.getPasswordHash())) {
            return generateToken(userDto);
        }
        else {
            throw new UserException("Wrong password provided", HttpStatus.UNAUTHORIZED);
        }
    }

    private String generateToken(UserDto userDto) {
        Algorithm algorithm = Algorithm.HMAC512("not-a-secret-anymore");

        return JWT.create()
                .withSubject(userDto.getEmail())
                .withExpiresAt(new Date((new Date()).getTime() + 1000000))
                .withClaim("email", userDto.getEmail())
                .sign(algorithm);
    }

}