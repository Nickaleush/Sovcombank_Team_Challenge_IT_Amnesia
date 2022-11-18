package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.WelcomeScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomePresenterImpl
import dagger.Module

@Module
interface WelcomeModule {
    @WelcomeScope
    fun presenter(presenter: WelcomePresenterImpl): WelcomePresenter
}