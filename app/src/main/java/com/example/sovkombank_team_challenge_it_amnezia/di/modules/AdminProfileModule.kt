package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.AdminProfileScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile.AdminProfilePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile.AdminProfilePresenterImpl
import dagger.Module

@Module
interface AdminProfileModule {
    @AdminProfileScope
    fun presenter(presenter: AdminProfilePresenterImpl): AdminProfilePresenter
}