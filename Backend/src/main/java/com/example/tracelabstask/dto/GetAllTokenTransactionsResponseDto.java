package com.example.tracelabstask.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetAllTokenTransactionsResponseDto {
    private String status;
    private String message;
    private List<GetAllTokenTransactionDto> result;
}
