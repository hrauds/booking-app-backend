package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.UserDto;
import com.bookservice.gametimebooking.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name = "User", description = "User endpoints")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    private static final String ENDPOINT_NAME = "/user";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @Operation(summary = "Create a User")
    @ApiResponse(responseCode = "201", description = "User was successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserDto userDto) {
        log.info("Request to create a User");
        userService.createUser(userDto);
    }

    @Operation(summary = "Login with User details and get a token")
    @ApiResponse(responseCode = "200", description = "User was approved and token generated")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME +"/login")
    public String login(@RequestBody UserDto userDto) {
        log.info("Request to login a User");
        return userService.login(userDto);
    }
}
