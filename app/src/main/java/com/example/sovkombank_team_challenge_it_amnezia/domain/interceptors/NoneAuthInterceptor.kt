package com.example.sovkombank_team_challenge_it_amnezia.domain.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class NoneAuthInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(chain.request())
    }
}