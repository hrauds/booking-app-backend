//package com.bookservice.gametimebooking.controller;
//
//import com.bookservice.gametimebooking.dto.ResourceDto;
//import com.bookservice.gametimebooking.service.ResourceService;
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/resource")
//@AllArgsConstructor
//public class ResourceController {
//
//    private ResourceService resourceService;
//
//    @PostMapping
//    public ResourceDto createHouse(@RequestBody ResourceDto createResourceRequest){
//        return resourceService.createResource(createResourceRequest);
//    }
//
//    @GetMapping
//    public List<ResourceDto> getAllResources() {
//        return resourceService.getAllResources();
//    }
//
//    @GetMapping("/{id}")
//    public ResourceDto getResourceById(@PathVariable Long id) {
//        return resourceService.getResourceById(id);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void overwriteResourceById(@PathVariable Long id, @RequestBody ResourceDto request) {
//        resourceService.overwriteResourceById(id, request);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteResourceById(@PathVariable Long id) {
//        resourceService.deleteById(id);
//    }
//}
