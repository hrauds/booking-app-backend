package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.UserDto;
import com.bookservice.gametimebooking.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    private static final String ENDPOINT_NAME = "/user";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public void createUser(@RequestBody UserDto userDto) {
        log.info("Request to create a User");
        userService.createUser(userDto);
    }

    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME +"/login")
    public String login(@RequestBody UserDto userDto) {
        log.info("Request to login a User");
        return userService.login(userDto);
    }
}
