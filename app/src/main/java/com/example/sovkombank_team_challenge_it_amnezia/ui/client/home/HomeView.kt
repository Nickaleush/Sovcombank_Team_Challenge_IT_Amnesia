package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Account
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface HomeView : BaseView {
    fun showError(message: String?)
    fun initCurrenciesRecyclerView(currencyList:ArrayList<Quotation>)
    fun initAccountsRecyclerView(accountsList:ArrayList<Account>)
    fun hideSkeleton()
    fun initCurrencyList(currencyList:ArrayList<Quotation>)
    fun dismissBottomSheet()
    fun showSellBottomSheet()
    fun showToastSuccess()
}