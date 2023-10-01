package com.bookservice.gametimebooking.controller.customer.dto;

import com.bookservice.gametimebooking.model.Booking;
import java.util.Set;

public class CustomerDto {

    private Long id;

    private String name;

    private Set<Booking> bookings;

}
