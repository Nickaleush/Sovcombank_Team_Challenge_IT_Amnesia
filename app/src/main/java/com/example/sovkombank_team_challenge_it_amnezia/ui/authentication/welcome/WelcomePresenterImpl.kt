package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class WelcomePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<WelcomeView>(),
    WelcomePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: WelcomeView

    override fun start() = Unit

}