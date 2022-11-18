package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.LogoModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ProfileScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoFragment
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [LogoModule::class])
interface LogoComponent {
    fun inject(fragment: LogoFragment)
}