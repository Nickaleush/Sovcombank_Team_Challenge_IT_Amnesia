package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.MainScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.activity.MainPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.activity.MainPresenterImpl
import dagger.Module

@Module
interface MainModule {
    @MainScope
    fun presenter(presenter: MainPresenterImpl): MainPresenter
}