package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeView

interface RegistrationPresenter: BasePresenter {
    var view: RegistrationView
}