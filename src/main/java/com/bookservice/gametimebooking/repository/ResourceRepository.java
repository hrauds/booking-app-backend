package com.bookservice.gametimebooking.repository;

import com.bookservice.gametimebooking.model.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    List<Resource> findAllByServiceId(Long service_id);
}
