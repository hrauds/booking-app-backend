package com.bookservice.gametimebooking.service.customer;

import com.bookservice.gametimebooking.controller.customer.dto.CustomerDto;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public void addCustomer(CustomerDto customer) {
        Customer newCustomer = customerMapper.toEntity(customer);
        customerRepository.save(newCustomer);
    }

    public CustomerDto getCustomer(Long id) {
        var customer = customerRepository.getReferenceById(id);
        return customerMapper.toCustomerDto(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toCustomerDto).collect(Collectors.toList());
    }
}
