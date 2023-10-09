package com.bookservice.gametimebooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String address;

    @ManyToOne
    private Company company;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Service> services = new HashSet<>();

    @Builder
    public House(String address) {
        this.address = address;
    }

    public void addService(Service service) {
        services.add(service);
        service.setHouse(this);
    }
}
