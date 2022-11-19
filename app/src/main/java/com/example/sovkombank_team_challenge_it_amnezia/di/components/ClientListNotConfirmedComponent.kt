package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.ClientListNotConfirmedModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ClientListNotConfirmedScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientNotConfirmedList.ClientListNotConfirmedFragment
import dagger.Subcomponent

@ClientListNotConfirmedScope
@Subcomponent(modules = [ClientListNotConfirmedModule::class])
interface ClientListNotConfirmedComponent {
    fun inject(fragment: ClientListNotConfirmedFragment)
}
