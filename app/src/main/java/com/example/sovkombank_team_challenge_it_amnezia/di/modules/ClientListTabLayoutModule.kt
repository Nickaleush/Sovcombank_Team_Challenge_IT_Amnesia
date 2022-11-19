package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.ClientListTabLayoutScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListTabLayout.ClientListTabLayoutPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListTabLayout.ClientListTabLayoutPresenterImpl
import dagger.Module

@Module
interface ClientListTabLayoutModule {
    @ClientListTabLayoutScope
    fun presenter(presenter: ClientListTabLayoutPresenterImpl): ClientListTabLayoutPresenter
}