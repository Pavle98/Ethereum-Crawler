package com.example.tracelabstask.dto;


import lombok.Data;

import java.util.List;

@Data
public class UsdtTransactionsResponseDto {
    private String status;
    private String message;
    private List<UsdtTransactionDto> result;

}

