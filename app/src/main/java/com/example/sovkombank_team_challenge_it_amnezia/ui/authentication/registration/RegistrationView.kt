package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration

import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface RegistrationView : BaseView {
    fun showError(message: String?)
    fun showConfirmationDialog()
    fun navToCreateCode()
}