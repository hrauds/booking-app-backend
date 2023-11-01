//package com.bookservice.gametimebooking.controller;
//
//import com.bookservice.gametimebooking.dto.HouseDto;
//import com.bookservice.gametimebooking.service.HouseService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/house")
//@AllArgsConstructor
//public class HouseController {
//
//    private HouseService houseService;
//
//    @PostMapping
//    public HouseDto createHouse(@RequestBody HouseDto createHouseRequest){
//        return houseService.createHouse(createHouseRequest);
//    }
//
//    @GetMapping
//    public List<HouseDto> getAllHouses() {
//        return houseService.getAllHouses();
//    }
//
//    @GetMapping("/{id}")
//    public HouseDto getHouseById(@PathVariable Long id) {
//        return houseService.getHouseById(id);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void overwriteHouseById(@PathVariable Long id, @RequestBody HouseDto houseDto) {
//        houseService.overwriteHouseById(id, houseDto);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteHouseById(@PathVariable Long id) {
//        houseService.deleteById(id);
//    }
//}
