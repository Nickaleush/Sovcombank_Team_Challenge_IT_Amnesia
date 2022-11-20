package com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfilePresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfileView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TransactionHistoryPresenterImpl @Inject constructor(private val mainApi: MainApi) : BasePresenterImpl<TransactionHistoryView>(),
    TransactionHistoryPresenter {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override lateinit var view: TransactionHistoryView

    override fun start() = Unit

    @SuppressLint("CheckResult")
    override fun getTransactionHistory() {
        mainApi.getTransactionHistory()
            .subscribeOn(Schedulers.io())
            .observeOn(
                AndroidSchedulers.mainThread()
            )
            .subscribe({
                view.initTransactionHistoryResycler(it)
                // Log.d("01333", it.toString())
            },{
                //view.showError(it.message)
            })
    }

}