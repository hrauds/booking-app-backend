package com.bookservice.gametimebooking.repository;

import com.bookservice.gametimebooking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
