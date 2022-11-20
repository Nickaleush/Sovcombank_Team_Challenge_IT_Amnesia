package com.example.sovkombank_team_challenge_it_amnezia.domain.mainApi

import com.example.sovkombank_team_challenge_it_amnezia.di.modules.AccessToken
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.*
import io.reactivex.Single
import retrofit2.http.*
import java.time.LocalDate

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

    @GET("api/account")
    fun getAllUserAccounts(): Single<ArrayList<Account>>

    @GET("api/currency")
    fun getAllCurrencies(): Single<ArrayList<Quotation>>

    @POST("api/account/transaction")
    fun createNewTransaction(@Body transaction: Transaction): Single<ArrayList<Unit>>

    @POST("api/account")
    fun createNewAccount(@Body currencyName: CurrencyName): Single<Account>

    @POST("api/account/money/add")
    fun addMoneyToAccount(@Body accountToAdd: AccountOperation): Single<Unit>

    @POST("api/account/money/delete")
    fun deleteMoneyFromAccount(@Body accountToDelete: AccountOperation): Single<Unit>

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

    @GET("api/history")
    fun getTransactionHistory(): Single<MutableList<TransactionDTO>>

    @POST("api/stat")
    fun getStatistics(@Body statistics: Statistics): Single<MutableList<GetStatistics>>

}