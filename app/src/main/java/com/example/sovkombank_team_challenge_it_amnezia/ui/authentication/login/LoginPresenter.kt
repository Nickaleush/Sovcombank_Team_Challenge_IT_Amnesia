package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToLogin
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter

interface LoginPresenter: BasePresenter {
    var view: LoginView
    fun loginClient(userToLogin: UserToLogin)
}