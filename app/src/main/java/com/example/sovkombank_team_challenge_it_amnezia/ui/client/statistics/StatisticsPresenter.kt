package com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Statistics
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter

interface StatisticsPresenter: BasePresenter {
    var view: StatisticsView
    fun getStatistics(statistic: Statistics)
}