package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile

import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import javax.inject.Inject

class AdminProfilePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<AdminProfileView>(),
    AdminProfilePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: AdminProfileView

    override fun start() = Unit

}