package com.example.sovkombank_team_challenge_it_amnezia.domain.models

import java.math.BigDecimal

data class Quotation(
    var currencyDto: Currency,
    var currencyId: String,
    var nominal: BigDecimal,
    var value: String
)
