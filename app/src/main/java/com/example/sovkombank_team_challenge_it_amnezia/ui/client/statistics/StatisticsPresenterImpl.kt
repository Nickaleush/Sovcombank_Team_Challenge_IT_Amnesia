package com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Statistics
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StatisticsPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<StatisticsView>(),
    StatisticsPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: StatisticsView

    override fun start() = Unit

    @SuppressLint("CheckResult")
    override fun getStatistics(statistic: Statistics) {
        mainApi.getStatistics(statistic)
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

}