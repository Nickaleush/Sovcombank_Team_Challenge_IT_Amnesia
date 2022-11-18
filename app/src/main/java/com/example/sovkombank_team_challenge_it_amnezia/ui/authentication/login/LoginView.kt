package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface LoginView : BaseView {
    fun showError(message: String?)
}