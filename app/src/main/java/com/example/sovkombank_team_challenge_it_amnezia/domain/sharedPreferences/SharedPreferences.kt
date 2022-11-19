package com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class SharedPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    var accessToken: String?
        get() = sharedPreferences.getString(ACCESS_TOKEN, null)
        set(value) = sharedPreferences.edit {
            putString(ACCESS_TOKEN, value)
            apply()
            commit()
        }

    var pinCode: String?
        get() = sharedPreferences.getString(PIN_CODE, null)
        set(value) = sharedPreferences.edit {
            putString(PIN_CODE, value)
            apply()
            commit()
        }

    var userName: String?
        get() = sharedPreferences.getString(USER_NAME, null)
        set(value) = sharedPreferences.edit {
            putString(USER_NAME, value)
            apply()
            commit()
        }

    companion object {
        const val ACCESS_TOKEN = "ACCESS_TOKEN"
        const val PIN_CODE = "PIN_CODE"
        const val USER_NAME = "USER_NAME"
    }
}