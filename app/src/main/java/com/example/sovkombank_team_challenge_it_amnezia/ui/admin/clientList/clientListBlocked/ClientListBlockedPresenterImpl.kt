package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class ClientListBlockedPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<ClientListBlockedView>(),
    ClientListBlockedPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ClientListBlockedView

    override fun start() = Unit

}