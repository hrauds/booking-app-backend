package com.bookservice.gametimebooking.controller.customer;

import com.bookservice.gametimebooking.controller.customer.dto.CustomerDto;
import com.bookservice.gametimebooking.service.customer.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@AllArgsConstructor
public class CustomerController {

    private CustomerService customerService;

    @PostMapping
    public void addCustomer(@RequestBody CustomerDto customer) {
        customerService.addCustomer(customer);
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        return customerService.getCustomer(id);
    }
}
