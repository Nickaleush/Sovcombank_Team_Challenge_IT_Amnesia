package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.LoginModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.RegistrationScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login.LoginFragment
import dagger.Subcomponent

@RegistrationScope
@Subcomponent(modules = [LoginModule::class])
interface LoginComponent {
    fun inject(fragment: LoginFragment)
}