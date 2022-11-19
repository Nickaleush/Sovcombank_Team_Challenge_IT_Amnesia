package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Currency
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfilePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfileView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomePresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<HomeView>(),
    HomePresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: HomeView

    @SuppressLint("CheckResult")
    override fun getAllCurrencies() {
        mainApi.getAllCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                view.hideSkeleton()
                view.initCurrenciesRecyclerView(it)
                // Log.d("01333", it.toString())
            }, {
                view.showError(it.message)
            })
    }

    override fun getUserInfo() {
        TODO("Not yet implemented")
    }

    override fun start() = Unit

}