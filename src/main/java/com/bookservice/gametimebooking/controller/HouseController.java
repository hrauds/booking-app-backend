package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.HouseDto;
import com.bookservice.gametimebooking.service.HouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "House", description = "Houses endpoints")
@AllArgsConstructor
@Slf4j
public class HouseController {

    private HouseService houseService;

    private static final String ENDPOINT_NAME = "/house";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @Operation(summary = "Create a new House")
    @ApiResponse(responseCode = "201", description = "House was successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(ENDPOINT_NAME)
    @ResponseStatus(HttpStatus.CREATED)
    public HouseDto createHouse(@RequestBody HouseDto createHouseRequest){
        log.info("Request to create a House");
        return houseService.createHouse(createHouseRequest);
    }

    @Operation(summary = "Find all Houses")
    @ApiResponse(responseCode = "200", description = "Houses were found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<HouseDto> getAllHouses() {
        log.info("Request to get all Houses");
        return houseService.getAllHouses();
    }

    @Operation(summary = "Find specific House by its id")
    @ApiResponse(responseCode = "200", description = "House was successfully found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public HouseDto getHouseById(@PathVariable Long id) throws JsonProcessingException {
        log.info("Request to get a House by id");
        return houseService.getHouseById(id);
    }

    @Operation(summary = "Change House information")
    @ApiResponse(responseCode = "204", description = "House was changed")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteHouseById(@PathVariable Long id, @RequestBody HouseDto houseDto) {
        log.info("Request to update a House by id");
        houseService.overwriteHouseById(id, houseDto);
    }

    @Operation(summary = "Delete House by its id")
    @ApiResponse(responseCode = "204", description = "House was deleted")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteHouseById(@PathVariable Long id) {
        log.info("Request to delete a House by id");
        houseService.deleteById(id);
    }
}
