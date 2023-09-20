package com.bookservice.gametimebooking.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String ServiceName;

    @ManyToOne
    private House house;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Resource> resources = new HashSet<>();

    @Builder
    public Service(String serviceName) {
        ServiceName = serviceName;
    }

    public void addResource(Resource resource) {
        resources.add(resource);
        resource.setService(this);
    }
}
