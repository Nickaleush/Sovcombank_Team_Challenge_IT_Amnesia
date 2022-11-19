package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListTabLayout

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class ClientListTabLayoutPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<ClientListTabLayoutView>(),
    ClientListTabLayoutPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ClientListTabLayoutView

    override fun start() = Unit

}