package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.HouseDto;
import com.bookservice.gametimebooking.service.HouseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class HouseController {

    private HouseService houseService;

    private static final String ENDPOINT_NAME = "/house";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(ENDPOINT_NAME)
    public HouseDto createHouse(@RequestBody HouseDto createHouseRequest){
        log.info("Request to create a House");
        return houseService.createHouse(createHouseRequest);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<HouseDto> getAllHouses() {
        log.info("Request to get all Houses");
        return houseService.getAllHouses();
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public HouseDto getHouseById(@PathVariable Long id) throws JsonProcessingException {
        log.info("Request to get a House by id");
        return houseService.getHouseById(id);
    }

    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteHouseById(@PathVariable Long id, @RequestBody HouseDto houseDto) {
        log.info("Request to update a House by id");
        houseService.overwriteHouseById(id, houseDto);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    public void deleteHouseById(@PathVariable Long id) {
        log.info("Request to delete a House by id");
        houseService.deleteById(id);
    }
}
