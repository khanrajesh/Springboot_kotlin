package com.example.learn.datasource.mock

import com.example.learn.datasource.BankDataSource
import com.example.learn.model.Bank
import org.springframework.stereotype.Repository


@Repository //it get the application context ,and tell it is a spring boot bin,
class MockBankDataSource:BankDataSource {
    val banks =  listOf(
        Bank("asd",30.0,10),
        Bank("123",13.0,0),
        Bank("456",300.0,17),

        )
    override fun retrieveBanks(): Collection<Bank> =banks
    override fun retrieveBank(accountNumber: String): Bank = banks.first {it.accountNumber == accountNumber}

}