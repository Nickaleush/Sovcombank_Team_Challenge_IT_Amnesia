package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.AdminProfileModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.AdminProfileScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile.AdminProfileFragment
import dagger.Subcomponent

@AdminProfileScope
@Subcomponent(modules = [AdminProfileModule::class])
interface AdminProfileComponent {
    fun inject(fragment: AdminProfileFragment)
}