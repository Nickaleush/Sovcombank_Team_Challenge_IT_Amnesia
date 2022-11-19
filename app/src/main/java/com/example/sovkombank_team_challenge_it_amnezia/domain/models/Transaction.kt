package com.example.sovkombank_team_challenge_it_amnezia.domain.models

import java.math.BigDecimal
import java.util.*

data class Transaction(
    var amount: BigDecimal,
    var dstAccountId: UUID,
    var srcAccountId: UUID
)