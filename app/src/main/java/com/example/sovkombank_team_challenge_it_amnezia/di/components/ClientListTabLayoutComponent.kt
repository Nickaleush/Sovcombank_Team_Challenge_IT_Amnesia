package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.ClientListTabLayoutModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ClientListTabLayoutScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListTabLayout.ClientListTabLayoutFragment
import dagger.Subcomponent

@ClientListTabLayoutScope
@Subcomponent(modules = [ClientListTabLayoutModule::class])
interface ClientListTabLayoutComponent {
    fun inject(fragment: ClientListTabLayoutFragment)
}
