package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientListActive

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClientListActivePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<ClientListActiveView>(),
    ClientListActivePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ClientListActiveView
    @SuppressLint("CheckResult")
    override fun getActiveClients() {
        mainApi.getActiveClients()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                //view.hideSkeleton()
                view.initRecyclerViewActiveClient(it)
                // Log.d("01333", it.toString())
            },{
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun setDisableClient(userId: String) {
        mainApi.disableClient(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                //view.hideSkeleton()
                // Log.d("01333", it.toString())
            },{
                view.showError(it.message)
            })
    }


    override fun start() = Unit

}