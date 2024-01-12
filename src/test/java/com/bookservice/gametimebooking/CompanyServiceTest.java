package com.bookservice.gametimebooking;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.mapper.CompanyMapper;
import com.bookservice.gametimebooking.model.Company;
import com.bookservice.gametimebooking.repository.CompanyRepository;
import com.bookservice.gametimebooking.service.CompanyService;
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
class CompanyServiceTest {

    @Mock
    private CompanyMapper companyMapper;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void createCompanySuccess() {
        CompanyDto companyDto = new CompanyDto();
        Company company = new Company();

        when(companyMapper.toEntity(Mockito.any(CompanyDto.class))).thenReturn(company);
        when(companyRepository.save(Mockito.any(Company.class))).thenReturn(null);
        when(companyMapper.toDto(Mockito.any(Company.class))).thenReturn(companyDto);

        CompanyDto result = companyService.createCompany(companyDto);

        verify(companyMapper, times(1)).toEntity(companyDto);
        verify(companyRepository, times(1)).save(company);
        verify(companyMapper, times(1)).toDto(company);

        assertThat(result).isNotNull();
    }


    @Test
    void getAllCompaniesSuccess() {
        Company company = new Company();
        CompanyDto companyDto = new CompanyDto();

        when(companyRepository.findAll()).thenReturn(List.of(company));
        when(companyMapper.toDto(Mockito.any(Company.class))).thenReturn(companyDto);

        List<CompanyDto> result = companyService.getAllCompanies();

        verify(companyRepository, times(1)).findAll();
        verify(companyMapper, times(1)).toDto(company);

        assertThat(result).containsExactly(companyDto);
    }

    @Test
    void getCompanyByIdSuccess() {
        Long mockId = 1L;
        Company company = new Company();
        CompanyDto companyDto = new CompanyDto();

        when(companyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(company));
        when(companyMapper.toDto(Mockito.any(Company.class))).thenReturn(companyDto);

        CompanyDto result = companyService.getCompanyById(mockId);

        verify(companyRepository, times(1)).findById(mockId);
        verify(companyMapper, times(1)).toDto(company);

        assertThat(result).isEqualTo(companyDto);
    }

    @Test
    void deleteByIdSuccess() {
        Long mockId = 1L;

        when(companyRepository.existsById(Mockito.any(Long.class))).thenReturn(true);
        doNothing().when(companyRepository).deleteById(Mockito.anyLong());

        companyService.deleteById(mockId);

        verify(companyRepository, times(1)).existsById(mockId);
        verify(companyRepository, times(1)).deleteById(mockId);
    }
}
