package com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency

import android.annotation.SuppressLint
import android.widget.Toast
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Prediction
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Statistics
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PredictionCurrencyPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<PredictionCurrencyView>(),
    PredictionCurrencyPresenter {

    override fun start() = Unit

    override lateinit var view: PredictionCurrencyView

    @SuppressLint("CheckResult")
    override fun getPrediction(prediction: Prediction) {
        mainApi.getPrediction(prediction)
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                view.setupColumnGraph(it)
                // Log.d("01333", it.toString())
            },{
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun getAllCurrencies() {
        mainApi.getAllCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.initCurrencyList(it)
            }, {
                view.showError(it.message)
            })
    }



}