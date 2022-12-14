package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.annotation.SuppressLint
import com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi.MainApi
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.AccountOperation
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.CurrencyName
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Transaction
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BasePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeFragment.Companion.SELL_OPENED
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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.initCurrenciesRecyclerView(it)
                view.initCurrencyList(it)
            }, {
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun getAllUserAccount() {
        mainApi.getAllUserAccounts()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.initAccountsRecyclerView(it)
                if (SELL_OPENED) view.showSellBottomSheet()
                SELL_OPENED = false
            }, {
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun createNewAccount(currencyName: CurrencyName) {
        mainApi.createNewAccount(currencyName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissBottomSheet()
                getAllUserAccount()
                view.showToastSuccess()
            }, {
                view.dismissBottomSheet()
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun addMoneyToAccount(accountToAdd: AccountOperation) {
        mainApi.addMoneyToAccount(accountToAdd)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissBottomSheet()
                getAllUserAccount()
                view.showToastSuccess()
            }, {
                view.dismissBottomSheet()
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun deleteMoneyFromAccount(accountToDelete: AccountOperation) {
        mainApi.deleteMoneyFromAccount(accountToDelete)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissBottomSheet()
                getAllUserAccount()
                view.showToastSuccess()
            }, {
                view.dismissBottomSheet()
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun createBuyTransaction(transaction: Transaction) {
        mainApi.createBuyTransaction(transaction)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissBottomSheet()
                getAllUserAccount()
                view.showToastSuccess()
            }, {
                view.dismissBottomSheet()
                view.showError(it.message)
            })
    }

    @SuppressLint("CheckResult")
    override fun createSellTransaction(transaction: Transaction) {
        mainApi.createSellTransaction(transaction)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view.dismissBottomSheet()
                getAllUserAccount()
                view.showToastSuccess()
            }, {
                view.dismissBottomSheet()
                view.showError(it.message)
            })
    }


    override fun start() = Unit

}