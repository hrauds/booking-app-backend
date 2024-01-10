package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.CustomerMapper;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;
    private static final String CUSTOMER_NOT_FOUND_ERROR_MESSAGE = "Customer not found";

    public CustomerDto addCustomer(CustomerDto customerDto) {
        Customer newCustomer = customerMapper.toEntity(customerDto);

        Customer existingCustomer = customerRepository.findByEmail(newCustomer.getEmail());

        if (existingCustomer != null) {
            log.info("Customer already exists");
            return customerMapper.toDto(existingCustomer);
        }
        customerRepository.save(newCustomer);
        log.info("Customer was created");
        return customerMapper.toDto(newCustomer);
    }


    public CustomerDto getCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new UserException(CUSTOMER_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));
        log.info("Customer was successfully found");
        return customerMapper.toDto(customer);
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(customerMapper::toDto)
                .toList();
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new UserException(CUSTOMER_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        customerRepository.deleteById(id);
        log.info("Customer was successfully deleted");
    }
}
