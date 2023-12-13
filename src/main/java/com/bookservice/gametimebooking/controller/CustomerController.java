package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    private static final String ENDPOINT_NAME = "/customer";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Request to add a customer");
        return customerService.addCustomer(customerDto);
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<CustomerDto> getAllCustomers() {
        log.info("Request to get all Customers");
        return customerService.getAllCustomers();
    }

    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        log.info("Request to get a Customer by id");
        return customerService.getCustomer(id);
    }

    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        log.info("Request to update a Customer by id");
        customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        log.info("Request to delete a Customer by id");
        customerService.deleteCustomer(id);
    }

}
