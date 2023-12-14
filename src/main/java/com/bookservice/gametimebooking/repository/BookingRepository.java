package com.bookservice.gametimebooking.repository;

import com.bookservice.gametimebooking.model.Booking;
import com.bookservice.gametimebooking.model.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByResourceAndStartTimeAfterAndEndTimeBefore(Resource resource,
                                                                     LocalDateTime startTime,
                                                                     LocalDateTime endTime);

    Page<Booking> findByStartTimeAfter(LocalDateTime startTime, Pageable pageable);
}
