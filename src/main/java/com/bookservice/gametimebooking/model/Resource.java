package com.bookservice.gametimebooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String resourceName;

    @ManyToOne
    private Service service;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    @Builder
    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setResource(this);
    }
}
