package com.example.sovkombank_team_challenge_it_amnezia.domain.models

data class UserDTO(
    var authority: String,
    var credentials: String,
    var firstName: String,
    var id: String,
    var isEnabled: Boolean,
    var lastName: String,
    var middleName: String,
    var pushToken: String,
)
