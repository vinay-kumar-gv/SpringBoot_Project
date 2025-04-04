package com.example.countryapi.service;

import com.example.countryapi.model.Country;
import com.example.countryapi.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ExternalApiService {

    @Value("${external.api.countries}")
    private String countriesApiUrl;

    private final CountryRepository countryRepository;
    private final RestTemplate restTemplate;

    public ExternalApiService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
        this.restTemplate = new RestTemplate();
    }

    public void fetchAndStoreCountries() {
        try {
            Object[] countriesArray = restTemplate.getForObject(countriesApiUrl, Object[].class);

            if (countriesArray != null) {
                List<Country> countries = new ArrayList<>();

                for (Object countryObj : countriesArray) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> countryMap = (Map<String, Object>) countryObj;

                    Country country = new Country();

                    @SuppressWarnings("unchecked")
                    Map<String, Object> nameMap = (Map<String, Object>) countryMap.get("name");
                    if (nameMap != null && nameMap.get("common") != null) {
                        country.setName((String) nameMap.get("common"));
                    }


                    @SuppressWarnings("unchecked")
                    List<String> capitals = (List<String>) countryMap.get("capital");
                    if (capitals != null && !capitals.isEmpty()) {
                        country.setCapital(capitals.get(0));
                    }


                    if (countryMap.get("region") != null) {
                        country.setRegion((String) countryMap.get("region"));
                    }

                    if (countryMap.get("population") != null) {
                        country.setPopulation(Long.valueOf(countryMap.get("population").toString()));
                    }

                    if (country.getName() != null && !countryRepository.existsByName(country.getName())) {
                        countries.add(country);
                    }
                }

                countryRepository.saveAll(countries);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Scheduled(initialDelay = 0, fixedRate = 86400000)
    public void scheduledFetchCountries() {
        fetchAndStoreCountries();
    }
}