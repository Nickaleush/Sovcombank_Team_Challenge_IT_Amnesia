package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class LogoPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<LogoView>(),
    LogoPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: LogoView

    override fun start() = Unit

}