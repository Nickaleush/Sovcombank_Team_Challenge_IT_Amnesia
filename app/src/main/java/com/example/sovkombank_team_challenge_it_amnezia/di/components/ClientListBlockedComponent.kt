package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.ClientListBlockedModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ClientListBlockedScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked.ClientListBlockedFragment
import dagger.Subcomponent

@ClientListBlockedScope
@Subcomponent(modules = [ClientListBlockedModule::class])
interface ClientListBlockedComponent {
    fun inject(fragment: ClientListBlockedFragment)
}
