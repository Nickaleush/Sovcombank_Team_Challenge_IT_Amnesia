package com.example.sovkombank_team_challenge_it_amnezia.domain.models

import androidx.annotation.LayoutRes
import com.example.sovkombank_team_challenge_it_amnezia.R

enum class HomeButtonType(@LayoutRes val layoutRes: Int) {
    Deposit(R.layout.item_home_button),
    Buy(R.layout.item_home_button),
    Withdraw(R.layout.item_home_button),
    Sell(R.layout.item_home_button),
    Statistics(R.layout.item_home_button),
}