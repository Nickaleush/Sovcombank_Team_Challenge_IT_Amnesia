package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.LogoScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoPresenterImpl

import dagger.Module

@Module
interface LogoModule {
    @LogoScope
    fun presenter(presenter: LogoPresenterImpl): LogoPresenter
}