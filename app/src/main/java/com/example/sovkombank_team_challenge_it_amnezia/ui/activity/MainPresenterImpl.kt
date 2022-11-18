package com.example.sovkombank_team_challenge_it_amnezia.ui.activity

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.MainScope
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

@MainScope
class MainPresenterImpl  @Inject constructor() : BasePresenterImpl<MainView>(), MainPresenter {

    override fun start() = Unit
    override lateinit var view: MainView

}