package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class RegistrationPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<RegistrationView>(),
    RegistrationPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: RegistrationView

    override fun start() = Unit

}