package com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter

interface ProfilePresenter: BasePresenter {
    var view: ProfileView
    fun getUserInfo()
}