package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.AuthScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth.AuthPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth.AuthPresenterImpl
import dagger.Module

@Module
interface AuthModule {
    @AuthScope
    fun presenter(presenter: AuthPresenterImpl): AuthPresenter
}