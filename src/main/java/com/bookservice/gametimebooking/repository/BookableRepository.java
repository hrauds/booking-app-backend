package com.bookservice.gametimebooking.repository;

import com.bookservice.gametimebooking.model.Bookable;
import com.bookservice.gametimebooking.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface BookableRepository extends JpaRepository<Bookable, Long> {

    boolean existsByResourceAndDate(Resource resource, LocalDate date);

    Optional<Bookable> findByDateAndResource(LocalDate date, Resource resource);
}
