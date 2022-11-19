package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ClientListActiveScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive.ClientListActivePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive.ClientListActivePresenterImpl
import dagger.Module

@Module
interface ClientListActiveModule {
    @ClientListActiveScope
    fun presenter(presenter: ClientListActivePresenterImpl): ClientListActivePresenter
}