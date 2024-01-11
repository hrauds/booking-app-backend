package com.bookservice.gametimebooking.controller;

import com.bookservice.gametimebooking.dto.CompanyDto;
import com.bookservice.gametimebooking.service.CompanyService;
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
@Tag(name = "Company", description = "Company endpoints")
@AllArgsConstructor
@Slf4j
public class CompanyController {

    private CompanyService companyService;

    private static final String ENDPOINT_NAME = "/company";

    @Operation(summary = "Create Company")
    @ApiResponse(responseCode = "201", description = "Company was successfully created")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @PostMapping(ENDPOINT_NAME)
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto createCompany(@RequestBody CompanyDto companyDto){
        log.info("Request to create a company");
        return companyService.createCompany(companyDto);
    }

    @Operation(summary = "Find all Companies")
    @ApiResponse(responseCode = "200", description = "Companies were found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(ENDPOINT_NAME)
    public List<CompanyDto> getAlCompanies() {
        log.info("Request to get all companies");
        return companyService.getAllCompanies();
    }

    @Operation(summary = "Get specific Company by its id")
    @ApiResponse(responseCode = "200", description = "Company was found")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @GetMapping(ENDPOINT_NAME + "/{id}")
    public CompanyDto getCompanyById(@PathVariable Long id) {
        log.info("Request to get a company by id");
        return companyService.getCompanyById(id);
    }

    @Operation(summary = "Delete Company by its id")
    @ApiResponse(responseCode = "204", description = "Company was deleted")
    @ApiResponse(responseCode = "400", description = "Invalid data provided")
    @DeleteMapping(ENDPOINT_NAME + "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompanyById(@PathVariable Long id) {
        log.info("Request to delete a company by id");
        companyService.deleteById(id);
    }
}
