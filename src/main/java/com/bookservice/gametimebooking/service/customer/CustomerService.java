package com.bookservice.gametimebooking.service.customer;

import com.bookservice.gametimebooking.controller.customer.dto.CustomerDto;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public void addCustomer(CustomerDto customer) {
        Customer newCustomer = customerMapper.toEntity(customer);
        customerRepository.save(newCustomer);
    }
}
