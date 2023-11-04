package com.bookservice.gametimebooking.repository;

import com.bookservice.gametimebooking.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

    List<Service> findServicesByHouse_Id(Long id);
}
