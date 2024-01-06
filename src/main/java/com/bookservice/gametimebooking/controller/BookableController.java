package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.BookableDto;
import com.bookservice.gametimebooking.service.BookableService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@AllArgsConstructor
public class BookableController {

    private final BookableService bookableService;

    private static final String ENDPOINT_NAME = "/bookable";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(ENDPOINT_NAME + "/resource/{resourceId}")
    public void addBookableForResource(@PathVariable Long resourceId,
                                       @RequestParam String date,
                                       @RequestParam String startTime,
                                       @RequestParam String endTime){
        log.info("Adding Bookable for resource process begin");
        bookableService.addBookableForResource(resourceId, date, startTime, endTime);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{serviceId}/{date}")
    public List<BookableDto> getBookablesForServiceAndDate(@PathVariable Long serviceId, @PathVariable String date) {
        log.info("Request to get bookables for a service and date");
        return bookableService.getBookablesForServiceAndDate(serviceId, date);
    }

    @DeleteMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{bookableId}")
    public void deleteBookable(@PathVariable Long bookableId) {
        log.info("Deleting Bookable process begin");
        bookableService.deleteBookable(bookableId);
    }
}
