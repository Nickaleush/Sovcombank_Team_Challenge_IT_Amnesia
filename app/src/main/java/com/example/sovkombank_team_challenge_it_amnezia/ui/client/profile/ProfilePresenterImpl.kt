package com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<ProfileView>(),
    ProfilePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ProfileView

    override fun start() = Unit

    @SuppressLint("CheckResult")
    override fun getUserInfo() {
        mainApi.getUserInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                view.initUserInfo(it)
                sharedPreferences.userName = it.firstName
            },{
                view.showError(it.message)
            })
    }

}