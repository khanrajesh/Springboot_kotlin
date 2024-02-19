package com.example.learn.datasource

import com.example.learn.model.Bank


interface BankDataSource {
    fun retrieveBanks():Collection<Bank>
    fun retrieveBank(accountNumber: String):Bank
}