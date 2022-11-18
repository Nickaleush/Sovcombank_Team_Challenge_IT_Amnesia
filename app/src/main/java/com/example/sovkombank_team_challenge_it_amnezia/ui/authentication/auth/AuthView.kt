package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface AuthView : BaseView {
    fun showError(message: String?)
}