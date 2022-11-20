package com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface ProfileView : BaseView {
    fun showError(message: String?)
    fun initUserInfo(user: UserDTO)
}