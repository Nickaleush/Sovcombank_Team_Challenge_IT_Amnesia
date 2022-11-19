package com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.AccessToken
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.*
import io.reactivex.Single
import retrofit2.http.*

interface MainApi {

    @POST("api/confirmation/phone")
    fun confirmClientAccount(@Body code: Code): Single<AccessToken>

    @POST("api/confirmation/admin")
    fun confirmAdminAccount(@Body code: Code): Single<AccessToken>

    @GET("api/admin/user/clients/disabled")
    fun getBlockedClients(): Single<ArrayList<ClientDTO>>

    @GET("api/admin/user/clients/enabled")
    fun getActiveClients(): Single<ArrayList<ClientDTO>>

    @GET("api/admin/user/clients/unconfirmed")
    fun getNotConfirmedClients(): Single<ArrayList<ClientDTO>>

    @GET("api/admin/user/{userId}/confirm")
    fun confirmClient(@Path(value = "userId", encoded = true) userId: String): Single<Unit>

    @GET("api/admin/user/{userId}/disable")
    fun disableClient(@Path(value = "userId", encoded = true) userId: String): Single<Unit>

    @GET("api/admin/user/{userId}/enable")
    fun enableClient(@Path(value = "userId", encoded = true) userId: String): Single<Unit>

    @PATCH("api/user/pushToken")
    fun setPushToken(@Body pushToken: PushTokenModel): Single<UserToSignUp>

    @GET("api/user/info")
    fun getUserInfo(): Single<UserDTO>
}