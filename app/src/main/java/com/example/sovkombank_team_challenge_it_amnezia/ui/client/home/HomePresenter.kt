package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Currency
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfileView

interface HomePresenter: BasePresenter {
    var view: HomeView
    fun getAllCurrencies()
    fun getAllUserAccount()
}