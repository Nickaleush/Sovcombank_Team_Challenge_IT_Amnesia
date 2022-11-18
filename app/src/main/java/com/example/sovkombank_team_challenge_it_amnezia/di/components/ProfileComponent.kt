package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.ProfileModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ProfileScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.profile.ProfileFragment
import dagger.Subcomponent

@ProfileScope
@Subcomponent(modules = [ProfileModule::class])
interface ProfileComponent {
    fun inject(fragment: ProfileFragment)
}