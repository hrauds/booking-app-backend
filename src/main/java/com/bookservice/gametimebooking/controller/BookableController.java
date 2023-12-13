package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.service.BookableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class BookableController {

    private final BookableService bookableService;

    private static final String ENDPOINT_NAME = "/bookable";

    @PostMapping(ENDPOINT_NAME + "/resource/{resourceId}")
    public void addBookableForResource(@PathVariable Long resourceId,
                                       @RequestParam String date,
                                       @RequestParam String startTime,
                                       @RequestParam String endTime){
        log.info("Adding Bookable for resource process begin");
        bookableService.addBookableForResource(resourceId, date, startTime, endTime);
    }
}
