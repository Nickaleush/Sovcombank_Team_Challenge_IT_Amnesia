package com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.authorizationApi.AuthorizationApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Code
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FirebaseMessagingItAmnesiaPresenterImpl @Inject constructor(private val authorizationApi: AuthorizationApi): BasePresenterImpl<FirebaseMessagingItAmnesiaView>(), FirebaseMessagingItAmnesiaPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: FirebaseMessagingItAmnesiaView

    override fun start() = Unit

    @SuppressLint("CheckResult")
    override fun sendUpdateAccessToken(token: Code) {
        authorizationApi.sendUpdateAccessToken(token)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                sharedPreferences.accessToken = it.accessToken
                // Log.d("01333", it.toString())
            },{

            })
    }

}