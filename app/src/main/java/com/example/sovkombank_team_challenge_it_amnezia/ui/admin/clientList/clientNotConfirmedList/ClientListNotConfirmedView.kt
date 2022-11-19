package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientNotConfirmedList

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView
import java.util.ArrayList

interface ClientListNotConfirmedView : BaseView {
    fun showError(message: String?)

    fun initRecyclerViewNotConfirmedClient(notConfirmedClientsList: ArrayList<ClientDTO>)

    fun endConfirmClient()
}