package com.example.sovkombank_team_challenge_it_amnezia.ui.admin.clientList.clientNotConfirmedList

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ClientListNotConfirmedPresenterImpl@Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<ClientListNotConfirmedView>(),
    ClientListNotConfirmedPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: ClientListNotConfirmedView

    override fun start() = Unit

    @SuppressLint("CheckResult")
    override fun getNotConfirmedClients() {
        mainApi.getNotConfirmedClients()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                //view.hideSkeleton()
                view.initRecyclerViewNotConfirmedClient(it)
                // Log.d("01333", it.toString())
            },{
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun confirmClient(userId: String) {
        mainApi.confirmClient(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                view.endConfirmClient()
                // Log.d("01333", it.toString())
            },{
                view.showError(it.message)
            })
    }

}