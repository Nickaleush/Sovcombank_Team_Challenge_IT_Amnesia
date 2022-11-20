package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.AccountOperation
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.CurrencyName
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Transaction
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter

interface HomePresenter: BasePresenter {
    var view: HomeView
    fun getAllCurrencies()
    fun getAllUserAccount()
    fun createNewAccount(currencyName: CurrencyName)
    fun addMoneyToAccount(accountToAdd: AccountOperation)
    fun deleteMoneyFromAccount(accountToDelete: AccountOperation)
    fun createBuyTransaction(transaction: Transaction)
    fun createSellTransaction(transaction: Transaction)
}