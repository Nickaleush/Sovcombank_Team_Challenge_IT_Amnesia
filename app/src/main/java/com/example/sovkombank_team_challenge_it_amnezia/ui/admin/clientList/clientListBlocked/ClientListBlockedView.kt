package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView
import java.util.ArrayList

interface ClientListBlockedView: BaseView {
    fun showError(message: String?)

    fun initRecyclerViewBlockedClient(blockedClientsList: ArrayList<ClientDTO>)
}