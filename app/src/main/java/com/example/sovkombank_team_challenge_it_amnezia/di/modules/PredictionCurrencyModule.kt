package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.PredictionCurrencyScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency.PredictionCurrencyPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency.PredictionCurrencyPresenterImpl
import dagger.Module

@Module
interface PredictionCurrencyModule {
    @PredictionCurrencyScope
    fun presenter(presenter: PredictionCurrencyPresenterImpl): PredictionCurrencyPresenter
}