package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.AuthPagerModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.AuthScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager.AuthPagerFragment
import dagger.Subcomponent

@AuthScope
@Subcomponent(modules = [AuthPagerModule::class])
interface AuthPagerComponent {
    fun inject(fragment: AuthPagerFragment)
}