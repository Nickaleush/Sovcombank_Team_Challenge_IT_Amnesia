package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface AdminProfileView : BaseView {

    fun initUserInfo(user: UserDTO)
}