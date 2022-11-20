package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.StatisticsModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.StatisticsScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics.StatisticsFragment
import dagger.Subcomponent

@StatisticsScope
@Subcomponent(modules = [StatisticsModule::class])
interface StatisticsComponent {
    fun inject(fragment: StatisticsFragment)
}