package com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Prediction
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Statistics
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeView

interface PredictionCurrencyPresenter : BasePresenter {
    var view: PredictionCurrencyView
    fun getPrediction(prediction: Prediction)
    fun getAllCurrencies()
}