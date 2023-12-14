package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.BookingDto;
import com.bookservice.gametimebooking.dto.BookingInfoDto;
import com.bookservice.gametimebooking.service.BookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    private static final String ENDPOINT_NAME = "/booking";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{serviceId}/{date}")
    public List<BookingInfoDto> getBookedTimesAtDate(@PathVariable String date, @PathVariable Long serviceId) {
        log.info("Request to get booked times at some specific date");
        return bookingService.getBookedTimesAtDate(serviceId, date);
    }

    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public void bookResource(@RequestParam Long resourceId,
                             @RequestParam String date,
                             @RequestParam String startTime,
                             @RequestParam String endTime,
                             @RequestParam Long customerId) {
        log.info("Request to book a resource");
        bookingService.bookResource(resourceId, date, startTime, endTime, customerId);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{bookingId}")
    public void deleteBooking(@PathVariable Long bookingId) {
        log.info("Request to delete a booking");
        bookingService.deleteBooking(bookingId);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/customize")
    public List<BookingDto> getCustomizedBookedTimes(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size,
                                                     @RequestParam(defaultValue = "id") String property,
                                                     @RequestParam(defaultValue = "ASC") String dir,
                                                     @RequestParam(defaultValue = "1900-12-14T12:30:00") String fromStartDate) {
        return bookingService.getCustomizedBookedTimes(page, size, property, dir, fromStartDate);
    }
}
