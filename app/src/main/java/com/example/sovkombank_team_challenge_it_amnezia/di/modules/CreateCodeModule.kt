package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.CreateCodeScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.createCode.CreateCodePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.createCode.CreateCodePresenterImpl

import dagger.Module

@Module
interface CreateCodeModule {
    @CreateCodeScope
    fun presenter(presenter: CreateCodePresenterImpl): CreateCodePresenter
}