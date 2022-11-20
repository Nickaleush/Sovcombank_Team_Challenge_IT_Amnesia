package com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeView
import javax.inject.Inject

class PredictionCurrencyPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<PredictionCurrencyView>(),
    PredictionCurrencyPresenter {

    override fun start() = Unit

    override lateinit var view: PredictionCurrencyView


}