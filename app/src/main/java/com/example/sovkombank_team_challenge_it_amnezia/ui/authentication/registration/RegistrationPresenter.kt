package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Code
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToSignUp
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeView

interface RegistrationPresenter: BasePresenter {
    var view: RegistrationView
    fun signUpClient(userToSignUp: UserToSignUp)
    fun confirmAccount(code: Code)
//    fun signUpAdmin(userToSignUp: UserToSignUp)
}