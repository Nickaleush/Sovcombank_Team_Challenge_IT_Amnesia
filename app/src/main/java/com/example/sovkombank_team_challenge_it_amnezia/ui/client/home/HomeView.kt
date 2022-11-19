package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface HomeView : BaseView {
    fun showError(message: String?)
}