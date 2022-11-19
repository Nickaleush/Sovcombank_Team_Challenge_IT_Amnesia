package com.example.sovkombank_team_challenge_it_amnezia.di.components

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.TransactionHistoryModule
import com.example.sovkombank_team_challenge_it_amnezia.di.scopes.TransactionHistoryScope
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory.TransactionHistoryFragment
import dagger.Subcomponent

@TransactionHistoryScope
@Subcomponent(modules = [TransactionHistoryModule::class])
interface TransactionHistoryComponent {
    fun inject(fragment: TransactionHistoryFragment)
}