package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    @PostMapping
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        return companyService.createCompany(companyDto);
    }

    @GetMapping
    public List<CompanyDto> getAlCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteCompanyById(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        companyService.overwriteCompanyById(id, companyDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}
