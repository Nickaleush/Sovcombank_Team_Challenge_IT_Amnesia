package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListBlocked

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject

class ClientListBlockedPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<ClientListBlockedView>(),
    ClientListBlockedPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ClientListBlockedView

    override fun start() = Unit

    @SuppressLint("CheckResult")
    override fun getBlockedClients() {
        mainApi.getActiveClients()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                //view.hideSkeleton()
                view.initRecyclerViewBlockedClient(it)
                // Log.d("01333", it.toString())
            },{
                view.showError(it.message)
            })
    }

}