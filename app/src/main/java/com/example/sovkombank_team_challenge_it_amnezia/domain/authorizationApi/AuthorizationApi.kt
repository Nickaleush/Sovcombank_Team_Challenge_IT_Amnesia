package com.example.sovkombank_team_challenge_it_amnezia.domain.authorizationApi

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.AccessToken
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Code
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToLogin
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToSignUp
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthorizationApi {

    @POST("api/auth/client/logIn")
    fun loginClient(@Body user: UserToLogin): Single<AccessToken>

    @POST("api/auth/client/signUp")
    fun signUpClient(@Body user: UserToSignUp): Single<AccessToken>

    @POST("api/auth/admin/logIn")
    fun loginAdmin(@Body user: UserToLogin): Single<AccessToken>

    @POST("api/auth/admin/signUp")
    fun signUpAdmin(@Body user: UserToSignUp): Single<AccessToken>

}