package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.StatisticsScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics.StatisticsPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics.StatisticsPresenterImpl
import dagger.Module

@Module
interface StatisticsModule {
    @StatisticsScope
    fun presenter(presenter: StatisticsPresenterImpl): StatisticsPresenter
}