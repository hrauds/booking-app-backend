package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.BookableDto;
import com.bookservice.gametimebooking.service.BookableService;
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
@Tag(name = "Bookable", description = "Bookables endpoints")
@Slf4j
@AllArgsConstructor
public class BookableController {

    private final BookableService bookableService;

    private static final String ENDPOINT_NAME = "/bookable";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @Operation(summary = "Add Bookable for Resource")
    @ApiResponse(responseCode = "201", description = "Bookable was successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(ENDPOINT_NAME + "/resource/{resourceId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addBookableForResource(@PathVariable Long resourceId,
                                       @RequestParam String date,
                                       @RequestParam String startTime,
                                       @RequestParam String endTime){
        log.info("Adding Bookable for resource process begin");
        bookableService.addBookableForResource(resourceId, date, startTime, endTime);
    }

    @Operation(summary = "Get all Bookables according to Service and Date")
    @ApiResponse(responseCode = "200", description = "Bookables found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{serviceId}/{date}")
    public List<BookableDto> getBookablesForServiceAndDate(@PathVariable Long serviceId, @PathVariable String date) {
        log.info("Request to get bookables for a service and date");
        return bookableService.getBookablesForServiceAndDate(serviceId, date);
    }

    @Operation(summary = "Delete Bookable by its id")
    @ApiResponse(responseCode = "204", description = "Bookable deleted")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @DeleteMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{bookableId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookable(@PathVariable Long bookableId) {
        log.info("Deleting Bookable process begin");
        bookableService.deleteBookable(bookableId);
    }
}
