package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.authorizationApi.AuthorizationApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToLogin
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.logo.LogoView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(private val authorizationApi: AuthorizationApi, private val mainApi: MainApi) : BasePresenterImpl<LoginView>(),
    LoginPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: LoginView

    @SuppressLint("CheckResult")
    override fun loginClient(userToLogin: UserToLogin) {
        authorizationApi.loginClient(userToLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                sharedPreferences.accessToken = it.accessToken
                view.navToMainFlow()
            }, {
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun loginAdmin(userToLogin: UserToLogin) {
        authorizationApi.loginAdmin(userToLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                sharedPreferences.accessToken = it.accessToken
                view.navToMainFlow()
            }, {
                view.showError(it.message)
            })
    }

    override fun start() = Unit

}