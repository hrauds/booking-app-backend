package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.addCustomer(customerDto);
    }

    @GetMapping
    public List<CustomerDto> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
        customerService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

}
