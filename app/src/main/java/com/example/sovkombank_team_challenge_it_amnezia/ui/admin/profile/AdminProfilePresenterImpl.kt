package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.profile

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AdminProfilePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<AdminProfileView>(),
    AdminProfilePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: AdminProfileView

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
                // Log.d("01333", it.toString())
            },{
                //view.showError(it.message)
            })
    }

}