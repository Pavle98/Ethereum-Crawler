package com.example.tracelabstask.dto;


import lombok.Data;

import java.util.List;

@Data
public class EthereumTransactionsResponseDto {
        private String status;
        private String message;
        private List<EthereumTransactionDto> result;

}