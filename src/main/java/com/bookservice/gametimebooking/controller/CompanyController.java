package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.service.CompanyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class CompanyController {

    private CompanyService companyService;

    private static final String ENDPOINT_NAME = "/company";
    private static final String OPEN_ENDPOINT_PREFIX = "/open";

    @PostMapping(ENDPOINT_NAME)
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        log.info("Request to create a company");
        return companyService.createCompany(companyDto);
    }

    @GetMapping(ENDPOINT_NAME)
    public List<CompanyDto> getAlCompanies() {
        log.info("Request to get all companies");
        return companyService.getAllCompanies();
    }

    @GetMapping(ENDPOINT_NAME + "/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        log.info("Request to get a company by id");
        return companyService.getCompanyById(id);
    }

    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        log.info("Request to delete a company by id");
        companyService.deleteById(id);
    }
}
