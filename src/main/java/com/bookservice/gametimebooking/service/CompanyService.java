package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.CompanyMapper;
import com.bookservice.gametimebooking.model.Company;
import com.bookservice.gametimebooking.repository.CompanyRepository;
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
public class CompanyService {

    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;
    private static final String COMPANY_NOT_FOUND_ERROR_MESSAGE = "Company not found";

    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        companyRepository.save(company);
        log.info("Company was successfully created and saved");
        return companyMapper.toDto(company);
    }

    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toDto)
                .toList();
    }

    public CompanyDto getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new UserException(COMPANY_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));
        log.info("Company was successfully found");
        return companyMapper.toDto(company);
    }

    public void deleteById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new UserException(COMPANY_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        companyRepository.deleteById(id);
        log.info("Company was successfully deleted");
    }
}
