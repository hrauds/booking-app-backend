package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.BookingInfoDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.model.Bookable;
import com.bookservice.gametimebooking.model.Booking;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.BookableRepository;
import com.bookservice.gametimebooking.repository.BookingRepository;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class BookingService {

    private final ResourceRepository resourceRepository;
    private final BookingRepository bookingRepository;
    private final BookableRepository bookableRepository;
    private final CustomerRepository customerRepository;

    public List<BookingInfoDto> getBookedTimesAtDate(Long serviceId, String date) {

        List<BookingInfoDto> bookingInfoDtos = new ArrayList<>();

        List<Resource> resources = resourceRepository.findAllByServiceId(serviceId);

        LocalDate localDate = LocalDate.parse(date);

        for (Resource resource: resources) {

            BookingInfoDto bookingInfoDto = new BookingInfoDto();

            Bookable bookableAtDate = bookableRepository.findByDateAndResource(localDate, resource)
                    .orElseThrow(() -> new UserException("No time available at given date", HttpStatus.NOT_FOUND));

            List<Booking> bookingsAtDate = bookingRepository.findAllByResourceAndStartTimeAfterAndEndTimeBefore(
                    resource, localDate.atStartOfDay(), localDate.atTime(LocalTime.MAX)
            );

            bookingInfoDto.setEarliestPossible(bookableAtDate.getStartTime());
            bookingInfoDto.setLatestPossible(bookableAtDate.getStartTime());
            bookingInfoDto.addBookingTimes(bookingsAtDate);

            bookingInfoDto.setResourceId(resource.getId());

            bookingInfoDtos.add(bookingInfoDto);
        }
        log.info("Successfully returning booking info");
        return bookingInfoDtos;
    }

    public void bookResource(Long resourceId, String date, String startTime, String endTime, Long customerId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new UserException("Wrong customer id provided", HttpStatus.NOT_FOUND));

        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new UserException("Wrong resource id provided", HttpStatus.NOT_FOUND));

        Booking booking = new Booking();

        LocalTime localStartTime = LocalTime.parse(startTime);
        LocalTime localEndTime = LocalTime.parse(endTime);

        LocalDate localDate = LocalDate.parse(date);

        booking.setStartTime(localDate.atTime(localStartTime));
        booking.setEndTime(localDate.atTime(localEndTime));

        booking.setCustomer(customer);
        booking.setResource(resource);

        bookingRepository.save(booking);
        log.info("Booking was successfully saved");
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
        log.info("Booking was successfully deleted");
    }
}
