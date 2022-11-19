package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Account
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface HomeView : BaseView {
    fun showError(message: String?)
    fun initCurrenciesRecyclerView(currencyList:ArrayList<Quotation>)
    fun initAccountsRecyclerView(currencyList:ArrayList<Account>)
    fun hideSkeleton()
}