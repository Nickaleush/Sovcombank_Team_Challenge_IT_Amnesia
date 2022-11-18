package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.AuthModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.AuthScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth.AuthFragment
import dagger.Subcomponent

@AuthScope
@Subcomponent(modules = [AuthModule::class])
interface AuthComponent {
    fun inject(fragment: AuthFragment)
}