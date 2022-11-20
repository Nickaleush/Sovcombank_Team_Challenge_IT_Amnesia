package com.example.sovkombank_team_challenge_it_amnezia.domain.models

import java.math.BigDecimal
import java.util.*

data class AccountOperation(
    var accountId: UUID,
    var amount: BigDecimal)