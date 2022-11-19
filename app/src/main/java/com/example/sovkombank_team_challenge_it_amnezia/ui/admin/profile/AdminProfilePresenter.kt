package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked.ClientListBlockedView

interface AdminProfilePresenter : BasePresenter {

    var view: AdminProfileView

    fun getUserInfo()
}