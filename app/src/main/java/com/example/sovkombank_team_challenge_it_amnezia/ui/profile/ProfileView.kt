package com.example.sovkombank_team_challenge_it_amnezia.ui.profile

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface ProfileView : BaseView {
    fun showError(message: String?)
}