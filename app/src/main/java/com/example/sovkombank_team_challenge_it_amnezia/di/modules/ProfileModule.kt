package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ProfileScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfilePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfilePresenterImpl
import dagger.Module

@Module
interface ProfileModule {
    @ProfileScope
    fun presenter(presenter: ProfilePresenterImpl): ProfilePresenter
}