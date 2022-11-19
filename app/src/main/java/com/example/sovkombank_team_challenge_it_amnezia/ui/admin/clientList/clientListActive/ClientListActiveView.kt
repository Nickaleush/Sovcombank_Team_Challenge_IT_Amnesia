package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView
import java.util.ArrayList

interface ClientListActiveView: BaseView {
    fun showError(message: String?)
    fun initRecyclerViewActiveClient(activeClientsList: ArrayList<ClientDTO>)
}