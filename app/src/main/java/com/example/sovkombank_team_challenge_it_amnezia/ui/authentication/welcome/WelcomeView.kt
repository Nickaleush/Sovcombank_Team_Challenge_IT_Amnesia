package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface WelcomeView : BaseView {
    fun showError(message: String?)
}