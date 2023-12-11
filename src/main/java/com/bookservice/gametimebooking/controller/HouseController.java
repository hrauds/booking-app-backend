package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.HouseDto;
import com.bookservice.gametimebooking.service.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class HouseController {

    private HouseService houseService;

    private static final String ENDPOINT_NAME = "/house";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(ENDPOINT_NAME)
    public HouseDto createHouse(@RequestBody HouseDto createHouseRequest){
        return houseService.createHouse(createHouseRequest);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<HouseDto> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public HouseDto getHouseById(@PathVariable Long id) {
        return houseService.getHouseById(id);
    }

    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteHouseById(@PathVariable Long id, @RequestBody HouseDto houseDto) {
        houseService.overwriteHouseById(id, houseDto);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    public void deleteHouseById(@PathVariable Long id) {
        houseService.deleteById(id);
    }
}
