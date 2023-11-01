package com.bookservice.gametimebooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String resourceName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Service service;

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    @OneToMany(mappedBy = "resource", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Bookable> bookables = new HashSet<>();

    @Builder
    public Resource(String resourceName) {
        this.resourceName = resourceName;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setResource(this);
    }

    public void addBookable(Bookable bookable) {
        bookables.add(bookable);
        bookable.setResource(this);
    }
}
