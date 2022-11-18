package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoView
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<LoginView>(),
    LoginPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: LoginView

    override fun start() = Unit

}