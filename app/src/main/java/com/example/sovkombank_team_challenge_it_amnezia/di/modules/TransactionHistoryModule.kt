package com.example.sovkombank_team_challenge_it_amnezia.di.modules

import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.TransactionHistoryScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory.TransactionHistoryPresenter
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory.TransactionHistoryPresenterImpl
import dagger.Module

@Module
interface TransactionHistoryModule {
    @TransactionHistoryScope
    fun presenter(presenter: TransactionHistoryPresenterImpl): TransactionHistoryPresenter
}