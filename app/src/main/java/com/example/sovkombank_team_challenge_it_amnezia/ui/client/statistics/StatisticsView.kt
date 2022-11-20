package com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.GetStatistics
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface StatisticsView:BaseView {
    fun showError(message: String?)
    fun initCurrencyList(currencyList: ArrayList<Quotation>)
    fun setupColumnGraph(statistic: MutableList<GetStatistics>)
}