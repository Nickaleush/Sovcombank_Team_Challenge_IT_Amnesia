package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.MainModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.MainScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.activity.MainActivity
import dagger.Subcomponent

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}