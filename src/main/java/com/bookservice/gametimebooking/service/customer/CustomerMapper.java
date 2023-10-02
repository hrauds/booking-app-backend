package com.bookservice.gametimebooking.service.customer;

import com.bookservice.gametimebooking.controller.customer.dto.CustomerDto;
import com.bookservice.gametimebooking.model.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private final ModelMapper modelMapper;

    public CustomerMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Customer toEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    public CustomerDto toCustomerDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

}
