package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.PredictionCurrencyModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.PredictionCurrencyScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency.PredictionCurrencyFragment
import dagger.Subcomponent

@PredictionCurrencyScope
@Subcomponent(modules = [PredictionCurrencyModule::class])
interface PredictionCurrencyComponent {
    fun inject(activity: PredictionCurrencyFragment)
}