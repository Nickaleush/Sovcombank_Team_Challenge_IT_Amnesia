package com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.GetStatistics
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface PredictionCurrencyView: BaseView {
    fun showError(message: String?)
    fun initCurrencyList(currencyList: ArrayList<Quotation>)
    fun setupColumnGraph(prediction: MutableList<GetStatistics>)
}