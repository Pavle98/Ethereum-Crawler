package com.example.tracelabstask.dto;

import lombok.Data;

@Data
public class LastBlockDto {
    private String jsonrpc;
    private int id;
    private String result;
}
