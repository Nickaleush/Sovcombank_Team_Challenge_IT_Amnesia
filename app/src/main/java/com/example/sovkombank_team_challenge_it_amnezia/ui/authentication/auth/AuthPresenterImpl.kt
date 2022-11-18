package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class AuthPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<AuthView>(),
    AuthPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: AuthView

    override fun start() = Unit

}