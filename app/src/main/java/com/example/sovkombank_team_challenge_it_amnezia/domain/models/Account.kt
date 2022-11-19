package com.example.sovkombank_team_challenge_it_amnezia.domain.models

import java.math.BigDecimal
import java.util.*

data class Account(
    var accountNumber: String,
    var amount: BigDecimal,
    var currency: Currency,
    var id: UUID,
)