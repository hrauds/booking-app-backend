package com.bookservice.gametimebooking.controller.customer;

import com.bookservice.gametimebooking.controller.customer.dto.CustomerDto;
import com.bookservice.gametimebooking.service.customer.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    public CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customer")
    public void addCustomer(@RequestBody CustomerDto customer) {
        customerService.addCustomer(customer);
    }
}
