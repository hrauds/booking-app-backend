package com.bookservice.gametimebooking.repository;

import com.bookservice.gametimebooking.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
}
