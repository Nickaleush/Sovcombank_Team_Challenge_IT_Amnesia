package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.RegistrationModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.RegistrationScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration.RegistrationFragment
import dagger.Subcomponent

@RegistrationScope
@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {
    fun inject(fragment: RegistrationFragment)
}