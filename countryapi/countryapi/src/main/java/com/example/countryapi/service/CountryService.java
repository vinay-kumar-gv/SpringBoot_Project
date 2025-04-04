package com.example.countryapi.service;

import com.example.countryapi.dto.CountryDto;
import com.example.countryapi.exception.ResourceNotFoundException;
import com.example.countryapi.model.Country;
import com.example.countryapi.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

   public List<CountryDto> getAllCountries() {
       return countryRepository.findAll().stream()
                .map(this::mapToDto)
               .collect(Collectors.toList());
   }

    public CountryDto getCountryById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + id));
        return mapToDto(country);
    }

    public CountryDto createCountry(CountryDto countryDto) {

        if (countryRepository.existsByName(countryDto.getName())) {
            throw new RuntimeException("Country with name " + countryDto.getName() + " already exists");
        }

        Country country = mapToEntity(countryDto);
        Country savedCountry = countryRepository.save(country);
        return mapToDto(savedCountry);
    }

    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + id));

        country.setName(countryDto.getName());
        country.setCapital(countryDto.getCapital());
        country.setRegion(countryDto.getRegion());
        country.setPopulation(countryDto.getPopulation());


        Country updatedCountry = countryRepository.save(country);
        return mapToDto(updatedCountry);
    }

    public void deleteCountry(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with id: " + id));

        countryRepository.delete(country);
    }

    private CountryDto mapToDto(Country country) {
        CountryDto countryDto = new CountryDto();
        countryDto.setId(country.getId());
        countryDto.setName(country.getName());
        countryDto.setCapital(country.getCapital());
        countryDto.setRegion(country.getRegion());
        countryDto.setPopulation(country.getPopulation());

        return countryDto;
    }

    private Country mapToEntity(CountryDto countryDto) {
        Country country = new Country();
        country.setName(countryDto.getName());
        country.setCapital(countryDto.getCapital());
        country.setRegion(countryDto.getRegion());
        country.setPopulation(countryDto.getPopulation());
        return country;
    }

}



