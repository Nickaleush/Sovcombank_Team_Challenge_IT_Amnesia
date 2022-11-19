package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ClientListBlockedScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked.ClientListBlockedPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked.ClientListBlockedPresenterImpl
import dagger.Module

@Module
interface ClientListBlockedModule {
    @ClientListBlockedScope
    fun presenter(presenter: ClientListBlockedPresenterImpl): ClientListBlockedPresenter
}