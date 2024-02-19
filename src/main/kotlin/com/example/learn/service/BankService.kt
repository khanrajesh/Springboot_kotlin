package com.example.learn.service

import com.example.learn.datasource.BankDataSource
import com.example.learn.model.Bank
import org.springframework.stereotype.Service


@Service //responsable for handeling and transforming data

class BankService(private val source: BankDataSource) {
    fun getBanks(): Collection<Bank> {
        return source.retrieveBanks()
    }

    fun getBank(accountNumber: String): Bank {
       return source.retrieveBank(accountNumber)
    }
}


