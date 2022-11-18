package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.createCode

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class CreateCodePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<CreateCodeView>(),
    CreateCodePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: CreateCodeView

    override fun start() = Unit

}