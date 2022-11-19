package com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Code

interface FirebaseMessagingItAmnesiaPresenter {

    var view: FirebaseMessagingItAmnesiaView

    fun sendUpdateAccessToken(token: Code)
}