package com.bookservice.gametimebooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bookables")
public class Bookable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate date;

    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Resource resource;

    @Builder
    public Bookable(LocalTime startTime, LocalTime endTime, Resource resource) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.resource = resource;
    }
}
