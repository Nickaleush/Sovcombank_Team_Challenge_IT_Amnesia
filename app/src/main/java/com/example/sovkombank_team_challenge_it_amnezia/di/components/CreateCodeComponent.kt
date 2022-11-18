package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.CreateCodeModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.CreateCodeScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.createCode.CreateCodeFragment
import dagger.Subcomponent


@CreateCodeScope
@Subcomponent(modules = [CreateCodeModule::class])
interface CreateCodeComponent {
    fun inject(fragment: CreateCodeFragment)
}
