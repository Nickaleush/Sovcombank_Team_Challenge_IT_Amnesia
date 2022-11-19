package com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.AccessToken
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Code
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface MainApi {

    @POST("api/confirmation/client")
    fun confirmClientAccount(@Body code: Code): Single<AccessToken>

    @POST("api/confirmation/admin")
    fun confirmAdminAccount(@Body code: Code): Single<AccessToken>

}