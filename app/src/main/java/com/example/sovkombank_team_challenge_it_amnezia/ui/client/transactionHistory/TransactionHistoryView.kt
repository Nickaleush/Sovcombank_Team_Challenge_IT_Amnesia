package com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory

import com.example.sovkombank_team_challenge_it_amnezia.domain.models.TransactionDTO
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseView

interface TransactionHistoryView: BaseView{
    fun initTransactionHistoryResycler(listTransactionHistory: MutableList<TransactionDTO>)
}