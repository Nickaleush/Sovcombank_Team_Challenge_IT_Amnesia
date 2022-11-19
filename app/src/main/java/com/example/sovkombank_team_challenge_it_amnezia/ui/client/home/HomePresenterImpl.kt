package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfilePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfileView
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<HomeView>(),
    HomePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: HomeView

    override fun start() = Unit

}