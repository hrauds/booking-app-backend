package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.mapper.CustomerMapper;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer newCustomer = customerMapper.toEntity(customerDto);
        customerRepository.save(newCustomer);
        return customerMapper.toDto(newCustomer);
    }

    public CustomerDto getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toDto).collect(Collectors.toList());
    }

    public void updateCustomer(Long id, CustomerDto customerDto) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customerMapper.partialUpdate(customer, customerDto);
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found" );
        }
        customerRepository.deleteById(id);
    }
}
