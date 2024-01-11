package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Tag(name = "Customer", description = "Customers endpoints")
@Slf4j
public class CustomerController {

    private CustomerService customerService;

    private static final String ENDPOINT_NAME = "/customer";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @Operation(summary = "Add Customer")
    @ApiResponse(responseCode = "201", description = "Customer was successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        log.info("Request to add a customer");
        return customerService.addCustomer(customerDto);
    }

    @Operation(summary = "Get all Customers")
    @ApiResponse(responseCode = "200", description = "Customers were found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME)
    public List<CustomerDto> getAllCustomers() {
        log.info("Request to get all Customers");
        return customerService.getAllCustomers();
    }

    @Operation(summary = "Get specific Customer by its id")
    @ApiResponse(responseCode = "200", description = "Customer was found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(OPEN_ENDPOINT_PREFIX + ENDPOINT_NAME + "/{id}")
    public CustomerDto getCustomer(@PathVariable Long id) {
        log.info("Request to get a Customer by id");
        return customerService.getCustomer(id);
    }

    @Operation(summary = "Delete Customer by its id")
    @ApiResponse(responseCode = "204", description = "Customer was deleted")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable Long id) {
        log.info("Request to delete a Customer by id");
        customerService.deleteCustomer(id);
    }

}
