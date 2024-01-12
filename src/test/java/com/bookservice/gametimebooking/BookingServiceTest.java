package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.BookingDto;
import com.bookservice.gametimebooking.dto.BookingInfoDto;
import com.bookservice.gametimebooking.mapper.BookingMapper;
import com.bookservice.gametimebooking.model.Bookable;
import com.bookservice.gametimebooking.model.Booking;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.model.Resource;
import com.bookservice.gametimebooking.repository.BookableRepository;
import com.bookservice.gametimebooking.repository.BookingRepository;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import com.bookservice.gametimebooking.repository.ResourceRepository;
import com.bookservice.gametimebooking.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookableRepository bookableRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookingMapper bookingMapper;

    @Captor
    private ArgumentCaptor<Booking> bookingArgumentCaptor;

    @InjectMocks
    private BookingService bookingService;

    @Test
    void getBookedTimesAtDateSuccess() {
        Long mockServiceId = 1L;
        String mockDate = "2024-01-10";
        Resource resource = new Resource();
        Bookable bookable = new Bookable();
        Booking booking = new Booking();
        booking.setStartTime(LocalDateTime.parse("2000-10-10T01:01:01"));
        booking.setEndTime(LocalDateTime.parse("2000-10-10T01:01:02"));


        when(resourceRepository.findAllByServiceId(Mockito.anyLong())).thenReturn(List.of(resource));
        when(bookableRepository.findByDateAndResource(Mockito.any(LocalDate.class), Mockito.any(Resource.class)))
                .thenReturn(Optional.of(bookable));
        when(bookingRepository.findAllByResourceAndStartTimeAfterAndEndTimeBefore(
                Mockito.any(Resource.class), Mockito.any(LocalDateTime.class), Mockito.any(LocalDateTime.class)))
                .thenReturn(List.of(booking));

        List<BookingInfoDto> result = bookingService.getBookedTimesAtDate(mockServiceId, mockDate);

        verify(resourceRepository, times(1)).findAllByServiceId(mockServiceId);
        verify(bookableRepository, times(1)).findByDateAndResource(LocalDate.parse(mockDate), resource);
        verify(bookingRepository, times(1)).findAllByResourceAndStartTimeAfterAndEndTimeBefore(
                resource, LocalDate.parse(mockDate).atStartOfDay(), LocalDate.parse(mockDate).atTime(LocalTime.MAX)
        );

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void bookResourceSuccess() {
        Long mockResourceId = 1L;
        String mockDate = "2024-01-10";
        String mockStartTime = "15:30:45";
        String mockEndTime = "16:30:45";
        Long mockCustomerId = 1L;
        Customer customer = new Customer();
        Resource resource = new Resource();

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
        when(resourceRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(resource));
        when(bookingRepository.save(Mockito.any(Booking.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        bookingService.bookResource(mockResourceId, mockDate, mockStartTime, mockEndTime, mockCustomerId);

        verify(customerRepository, times(1)).findById(mockCustomerId);
        verify(resourceRepository, times(1)).findById(mockResourceId);
        verify(bookingRepository, times(1)).save(bookingArgumentCaptor.capture());
        Booking capturedBooking = bookingArgumentCaptor.getValue();

        assertThat(capturedBooking).isNotNull();
    }

    @Test
    void deleteBookingSuccess() {
        Long mockBookingId = 1L;

        doNothing().when(bookingRepository).deleteById(Mockito.anyLong());

        bookingService.deleteBooking(mockBookingId);

        verify(bookingRepository, times(1)).deleteById(mockBookingId);

    }

    @Test
    void getCustomizedBookedTimesSuccess() {
        int mockPage = 0;
        int mockSize = 1;
        String mockProperty = "mock";
        String mockDir = "ASC";
        String mockStartTime = "2000-10-10T01:01:01";

        when(bookingRepository.findByStartTimeAfter(
                Mockito.any(LocalDateTime.class), Mockito.any(Pageable.class))).thenReturn(Page.empty());

        List<BookingDto> result = bookingService.getCustomizedBookedTimes(
                mockPage, mockSize, mockProperty, mockDir,mockStartTime);

        assertThat(result.size()).isEqualTo(0);
    }
}
