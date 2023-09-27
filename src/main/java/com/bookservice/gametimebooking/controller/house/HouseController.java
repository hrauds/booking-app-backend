package com.bookservice.gametimebooking.controller.house;

import com.bookservice.gametimebooking.controller.house.dto.CreateHouseRequest;
import com.bookservice.gametimebooking.controller.house.dto.CreateHouseResponse;
import com.bookservice.gametimebooking.controller.house.dto.HouseResponse;
import com.bookservice.gametimebooking.controller.house.dto.OverwriteHouseRequest;
import com.bookservice.gametimebooking.service.house.HouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/house")
@AllArgsConstructor
public class HouseController {

    private HouseService houseService;

    @PostMapping
    public CreateHouseResponse createHouse(@RequestBody CreateHouseRequest createHouseRequest){
        return houseService.createHouse(createHouseRequest);
    }

    @GetMapping
    public List<HouseResponse> getAllHouses() {
        return houseService.getAllHouses();
    }

    @GetMapping("/{id}")
    public HouseResponse getHouseById(@PathVariable Long id) {
        return houseService.getHouseById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteHouseById(@PathVariable Long id, @RequestBody OverwriteHouseRequest request) {
        houseService.overwriteHouseById(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteHouseById(@PathVariable Long id) {
        houseService.deleteById(id);
    }
}
