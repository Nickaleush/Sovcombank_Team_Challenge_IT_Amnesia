package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.HomeModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ProfileScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeFragment
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(fragment: HomeFragment)
}