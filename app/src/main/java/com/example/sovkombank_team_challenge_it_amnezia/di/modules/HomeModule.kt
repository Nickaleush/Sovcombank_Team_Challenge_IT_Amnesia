package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.HomeScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomePresenterImpl
import dagger.Module

@Module
interface HomeModule {
    @HomeScope
    fun presenter(presenter: HomePresenterImpl): HomePresenter
}