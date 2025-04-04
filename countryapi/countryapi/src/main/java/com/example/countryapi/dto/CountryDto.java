package com.example.countryapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryDto {
    private Long id;

    @NotBlank(message = "Country name is required")
    private String name;

    private String capital;
    private String region;
    private Long population;

}
