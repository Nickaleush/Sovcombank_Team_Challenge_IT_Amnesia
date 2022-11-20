package com.example.sovkombank_team_challenge_it_amnezia.domain.models

import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.*

data class TransactionDTO(
    var amount: BigDecimal,
    var dstAccount: Account?,
    val id: UUID,
    var srcAccount: Account,
    var time: OffsetDateTime,
    var type:String
)
