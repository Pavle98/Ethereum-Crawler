package com.example.tracelabstask.controllers;

import com.example.tracelabstask.dto.*;
import com.example.tracelabstask.services.EtherscanApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private EtherscanApiService etherscanApiService;

    @CrossOrigin
    @GetMapping("/getbalance/{address}")
    public EthereumBalanceAndUsdValueDto getBalance(@PathVariable String address) {

        return etherscanApiService.getBalance(address);
    }
    @GetMapping("/getvalueofeth")
    public Double getValueOfEth(){
        return etherscanApiService.getEthPriceInUsd();
    }
    @CrossOrigin
    @GetMapping("/getnormaltransactions/address={address}&startblock={startBlock}&endblock={endBlock}")
    public List<EthereumTransactionDto> getNormalTransactions(@PathVariable String address, @PathVariable String startBlock, @PathVariable String endBlock){
        return etherscanApiService.getNormalTransactions(address, startBlock, endBlock);
    }
    @CrossOrigin
    @GetMapping("/getinternaltransactions/address={address}&startblock={startBlock}&endblock={endBlock}")
    public List<EthereumTransactionDto>  getInternalTransactions(@PathVariable String address, @PathVariable String startBlock, @PathVariable String endBlock){
        return etherscanApiService.getInternalTransactions(address, startBlock, endBlock);
    }
    @CrossOrigin
    @GetMapping("/infura/address={address}&timestamp={timeStamp}")
    public double getHistoricalBalance(@PathVariable String address, @PathVariable long timeStamp){
        return etherscanApiService.getHistoricalBalance(address, timeStamp);
    }
    @CrossOrigin
    @GetMapping("/getusdttransactions/address={address}&startblock={startBlock}&endblock={endBlock}")
    public List<UsdtTransactionDto> getUsdtTransactions(@PathVariable String address, @PathVariable String startBlock, @PathVariable String endBlock){
        return etherscanApiService.getUsdtTransactions(address, startBlock, endBlock);
    }
    @CrossOrigin
    @GetMapping("/getusdtbalance/{address}")
    public Double getUsdtBalance(@PathVariable String address){
        return etherscanApiService.getUsdtBalance(address);
    }


    @CrossOrigin
    @GetMapping("/infura/gethistoricalusdtbalance/address={address}&timestamp={timeStamp}")
    public Double getHistoricalUsdtBalance(@PathVariable String address, @PathVariable long timeStamp){
        return etherscanApiService.getHistoricalUsdtBalance(address,timeStamp);
    }

    @CrossOrigin
    @GetMapping("/getalltokentransactions/address={address}&startblock={startBlock}&endblock={endBlock}")
    public List<GetAllTokenTransactionDto> getAllTokenTransaction(@PathVariable String address, @PathVariable String startBlock, @PathVariable String endBlock){
        return etherscanApiService.getAllTokenTransactions(address, startBlock, endBlock);
    }



}
