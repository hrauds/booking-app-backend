package com.bookservice.gametimebooking.repository;

import com.bookservice.gametimebooking.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
