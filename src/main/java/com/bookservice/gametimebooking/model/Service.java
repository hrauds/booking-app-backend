package com.bookservice.gametimebooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String serviceName;

    @ManyToOne
    @JoinColumn(nullable = false)
    private House house;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resource> resources = new HashSet<>();

    @Builder
    public Service(String serviceName) {
        this.serviceName = serviceName;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
        resource.setService(this);
    }
}
