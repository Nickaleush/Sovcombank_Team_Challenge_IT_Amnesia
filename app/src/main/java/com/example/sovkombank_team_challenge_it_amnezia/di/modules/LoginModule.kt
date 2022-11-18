package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.LoginScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login.LoginPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login.LoginPresenterImpl
import dagger.Module

@Module
interface LoginModule {
    @LoginScope
    fun presenter(presenter: LoginPresenterImpl): LoginPresenter
}