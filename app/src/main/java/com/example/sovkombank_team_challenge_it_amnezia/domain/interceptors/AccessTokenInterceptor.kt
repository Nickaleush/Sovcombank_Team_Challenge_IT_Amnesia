package com.example.sovkombank_team_challenge_it_amnezia.domain.interceptors

import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AccessTokenInterceptor @Inject constructor(private val sharedPreference: SharedPreferences) :
    Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val accessToken = "Bearer ${sharedPreference.accessToken}"
        val request = chain.request().newBuilder()
        if (accessToken.isNotEmpty()) {
            request.header("Authorization", accessToken)
        }
        return chain.proceed(request.build())
    }
}