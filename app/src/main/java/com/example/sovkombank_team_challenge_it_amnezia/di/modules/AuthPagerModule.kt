package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.AuthPagerScope
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.AuthScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager.AuthPagerPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager.AuthPagerPresenterImpl
import dagger.Module

@Module
interface AuthPagerModule {
    @AuthPagerScope
    fun presenter(presenter: AuthPagerPresenterImpl): AuthPagerPresenter
}