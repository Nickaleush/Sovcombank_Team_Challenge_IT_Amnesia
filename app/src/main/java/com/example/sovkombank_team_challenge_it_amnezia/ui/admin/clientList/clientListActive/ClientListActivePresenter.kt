package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter

interface ClientListActivePresenter : BasePresenter {

    var view: ClientListActiveView

    fun getActiveClients()
}