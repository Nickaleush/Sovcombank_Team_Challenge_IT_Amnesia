package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.authorizationApi.AuthorizationApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Code
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.PushTokenModel
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToSignUp
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RegistrationPresenterImpl @Inject constructor(private val authorizationApi: AuthorizationApi, private val mainApi: MainApi) : BasePresenterImpl<RegistrationView>(),
    RegistrationPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: RegistrationView

    @SuppressLint("CheckResult")
    override fun signUpClient(userToSignUp: UserToSignUp) {
        authorizationApi.signUpClient(userToSignUp)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                sharedPreferences.accessToken = it.accessToken
                view.showConfirmationDialog()
            }, {
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun signUpAdmin(userToSignUp: UserToSignUp) {
        authorizationApi.signUpAdmin(userToSignUp)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                sharedPreferences.accessToken = it.accessToken
                view.showConfirmationDialog()
            }, {
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun confirmClientAccount(code: Code) {
        mainApi.confirmClientAccount(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sharedPreferences.accessToken = it.accessToken
                view.navToCreateCode()
                view.setupPushToken()
            }, {
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun confirmAdminAccount(code: Code) {
        mainApi.confirmAdminAccount(code)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                sharedPreferences.accessToken = it.accessToken
                view.navToCreateCode()
            }, {
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun setupPushToken(pushToken: String) {
        mainApi.setPushToken(PushTokenModel(pushToken))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            },{})
    }

    override fun start() = Unit

}