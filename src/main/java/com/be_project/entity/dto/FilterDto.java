package com.be_project.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterDto {
    private String status = "";
    private String title = "";
    private String postSell = "";
    private String postBuy = "";
    private LocalDate startDate;
    private LocalDate endDate;
}
