package com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface StatisticsView:BaseView {
    fun showError(message: String?)
    fun setupColumnGraph(statistic: Map<String, Double>)
}