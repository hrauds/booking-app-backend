package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.exceptions.CustomerNotFoundException;
import com.bookservice.gametimebooking.mapper.CustomerMapper;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private static final String CUSTOMER_NOT_FOUND_ERROR_MESSAGE = "Customer not found";

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer newCustomer = customerMapper.toEntity(customerDto);
        customerRepository.save(newCustomer);
        return customerMapper.toDto(newCustomer);
    }

    public CustomerDto getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND_ERROR_MESSAGE));
        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(CUSTOMER_NOT_FOUND_ERROR_MESSAGE));
        customerMapper.partialUpdate(customer, customerDto);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND_ERROR_MESSAGE);
        }
        customerRepository.deleteById(id);
    }
}
