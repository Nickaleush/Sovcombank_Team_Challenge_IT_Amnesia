package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientNotConfirmedList

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter

interface ClientListNotConfirmedPresenter: BasePresenter {

    var view: ClientListNotConfirmedView

    fun getNotConfirmedClients()

    fun confirmClient(userId: String)
}