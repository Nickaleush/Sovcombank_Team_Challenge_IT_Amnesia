package com.example.sovkombank_team_challenge_it_amnezia.domain.models

data class UserToSignUp (
    val birthDate: String,
    val credentials: String,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val password: String
)