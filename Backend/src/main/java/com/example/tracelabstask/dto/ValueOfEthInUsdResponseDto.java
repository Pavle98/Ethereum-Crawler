package com.example.tracelabstask.dto;

import lombok.Data;

@Data
public class ValueOfEthInUsdResponseDto {
    private String status;
    private String message;
    private ValueOfEthInUsdDto result;
}
