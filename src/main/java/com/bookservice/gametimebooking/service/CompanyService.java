package com.bookservice.gametimebooking.service;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.exceptions.UserException;
import com.bookservice.gametimebooking.mapper.CompanyMapper;
import com.bookservice.gametimebooking.model.Company;
import com.bookservice.gametimebooking.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class CompanyService {

    private CompanyRepository companyRepository;
    private CompanyMapper companyMapper;
    private static final String COMPANY_NOT_FOUND_ERROR_MESSAGE = "Company not found";

    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.toEntity(companyDto);
        companyRepository.save(company);
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
        return companyMapper.toDto(company);
    }

    public void overwriteCompanyById(Long id, CompanyDto companyDto) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new UserException(COMPANY_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND));
        companyMapper.partialUpdate(company, companyDto);
        companyRepository.save(company);
    }

    public void deleteById(Long id) {
        if (!companyRepository.existsById(id)) {
            throw new UserException(COMPANY_NOT_FOUND_ERROR_MESSAGE, HttpStatus.NOT_FOUND);
        }
        companyRepository.deleteById(id);
    }
}
