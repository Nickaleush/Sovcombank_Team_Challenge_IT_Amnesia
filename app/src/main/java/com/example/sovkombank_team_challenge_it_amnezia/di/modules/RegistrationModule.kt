package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.RegistrationScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration.RegistrationPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration.RegistrationPresenterImpl
import dagger.Module

@Module
interface RegistrationModule {
    @RegistrationScope
    fun presenter(presenter: RegistrationPresenterImpl): RegistrationPresenter
}