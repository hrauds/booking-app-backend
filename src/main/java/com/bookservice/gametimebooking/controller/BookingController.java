package com.bookservice.gametimebooking.controller;

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
    private void deleteBooking(@PathVariable Long bookingId) {
        log.info("Request to delete a booking");
        bookingService.deleteBooking(bookingId);
    }
}
