package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class AuthPagerPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<AuthPagerView>(),
    AuthPagerPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: AuthPagerView

    override fun start() = Unit

}