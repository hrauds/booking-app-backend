package com.bookservice.gametimebooking.dto;

import com.bookservice.gametimebooking.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class BookingInfoDto {

    private Long resourceId;

    private LocalTime earliestPossible;

    private LocalTime latestPossible;

    private List<BookedTime> bookedTimes = new ArrayList<>();

    public void addBookingTimes(List<Booking> bookings) {
        if (bookedTimes == null) {
            bookedTimes = new ArrayList<>();
        }
        for (Booking booking: bookings) {
            bookedTimes.add(new BookedTime(booking.getStartTime().toLocalTime(), booking.getEndTime().toLocalTime()));
        }
    }

    @Data
    @AllArgsConstructor
    public static class BookedTime {

        private LocalTime startTime;

        private LocalTime endTime;
    }
}
