package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.WelcomeModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ProfileScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [WelcomeModule::class])
interface WelcomeComponent {
    fun inject(fragment: WelcomeFragment)
}