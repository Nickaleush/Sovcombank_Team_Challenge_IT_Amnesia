package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter

interface ClientListBlockedPresenter : BasePresenter {

    var view: ClientListBlockedView

    fun getBlockedClients()
}