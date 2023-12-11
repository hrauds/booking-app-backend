package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.UserDto;
import com.bookservice.gametimebooking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private static final String ENDPOINT_NAME = "/user";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public void createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
    }

    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME +"/login")
    public String login(@RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
