package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CompanyController {

    private CompanyService companyService;

    private static final String ENDPOINT_NAME = "/company";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(ENDPOINT_NAME)
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        return companyService.createCompany(companyDto);
    }

    @GetMapping(ENDPOINT_NAME)
    public List<CompanyDto> getAlCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping(ENDPOINT_NAME + "/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    @PutMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void overwriteCompanyById(@PathVariable Long id, @RequestBody CompanyDto companyDto) {
        companyService.overwriteCompanyById(id, companyDto);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}
