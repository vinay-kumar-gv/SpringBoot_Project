package com.example.countryapi.controller;

import com.example.countryapi.dto.CountryDto;
import com.example.countryapi.model.Country;
import com.example.countryapi.service.CountryService;
import com.example.countryapi.service.ExternalApiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Scanner;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;
    private final ExternalApiService externalApiService;

    public CountryController(CountryService countryService, ExternalApiService externalApiService) {
        this.countryService = countryService;
        this.externalApiService = externalApiService;
    }
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PostMapping
    public ResponseEntity<CountryDto> createCountry(@Valid @RequestBody CountryDto countryDto) {
        return new ResponseEntity<>(countryService.createCountry(countryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id,
                                                    @Valid @RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.updateCountry(id, countryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/fetch-external")
    public ResponseEntity<String> fetchExternalCountries() {
        externalApiService.fetchAndStoreCountries();
        return ResponseEntity.ok("Countries data fetch process started");
    }
}




