package com.example.sovkombank_team_challenge_it_amnezia.ui.profile

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class ProfilePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<ProfileView>(),
    ProfilePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ProfileView

    override fun start() = Unit

}