package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.ClientListActiveModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ClientListActiveScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive.ClientListActiveFragment
import dagger.Subcomponent

@ClientListActiveScope
@Subcomponent(modules = [ClientListActiveModule::class])
interface ClientListActiveComponent {
    fun inject(fragment: ClientListActiveFragment)
}
