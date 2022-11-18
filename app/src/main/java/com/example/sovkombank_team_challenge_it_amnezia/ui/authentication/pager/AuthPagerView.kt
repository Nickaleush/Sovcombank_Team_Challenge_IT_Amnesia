package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface AuthPagerView : BaseView {
    fun showError(message: String?)
}