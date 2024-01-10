package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.CustomerDto;
import com.bookservice.gametimebooking.mapper.CustomerMapper;
import com.bookservice.gametimebooking.model.Customer;
import com.bookservice.gametimebooking.repository.CustomerRepository;
import com.bookservice.gametimebooking.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void addCustomerSuccess() {
        Customer customer = new Customer();
        String mockEmail = "mockEmail";
        customer.setEmail(mockEmail);
        CustomerDto customerDto = new CustomerDto();

        when(customerMapper.toEntity(Mockito.any(CustomerDto.class))).thenReturn(customer);
        when(customerRepository.findByEmail(Mockito.anyString())).thenReturn(null);
        when(customerRepository.save(Mockito.any(Customer.class))).thenReturn(null);
        when(customerMapper.toDto(Mockito.any(Customer.class))).thenReturn(customerDto);

        CustomerDto result = customerService.addCustomer(customerDto);

        verify(customerMapper, times(1)).toEntity(customerDto);
        verify(customerRepository, times(1)).findByEmail(mockEmail);
        verify(customerRepository, times(1)).save(customer);
        verify(customerMapper, times(1)).toDto(customer);

        assertThat(result).isEqualTo(customerDto);
    }

    @Test
    void getCustomerSuccess() {
        Long mockId = 1L;
        Customer customer = new Customer();
        CustomerDto customerDto = new CustomerDto();

        when(customerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(customer));
        when(customerMapper.toDto(Mockito.any(Customer.class))).thenReturn(customerDto);

        CustomerDto result = customerService.getCustomer(mockId);

        verify(customerRepository, times(1)).findById(mockId);
        verify(customerMapper, times(1)).toDto(customer);

        assertThat(result).isEqualTo(customerDto);
    }

    @Test
    void getAllCustomersSuccess() {
        Customer customer = new Customer();
        CustomerDto customerDto = new CustomerDto();

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(customerMapper.toDto(Mockito.any(Customer.class))).thenReturn(customerDto);

        List<CustomerDto> result = customerService.getAllCustomers();

        verify(customerRepository, times(1)).findAll();
        verify(customerMapper, times(1)).toDto(customer);

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    void deleteCustomerSuccess() {
        Long mockId = 1L;

        when(customerRepository.existsById(Mockito.anyLong())).thenReturn(true);
        doNothing().when(customerRepository).deleteById(Mockito.anyLong());

        customerService.deleteCustomer(mockId);

        verify(customerRepository, times(1)).existsById(mockId);
        verify(customerRepository, times(1)).deleteById(mockId);
    }
}
