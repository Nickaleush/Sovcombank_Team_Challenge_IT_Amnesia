package com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Code
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import javax.inject.Inject

class FirebaseMessagingItAmnesiaService: FirebaseMessagingItAmnesiaView, FirebaseMessagingService()   {
    @Inject
    lateinit var presenter: FirebaseMessagingItAmnesiaPresenterImpl

    @Inject
    lateinit var sharedPreference: SharedPreferences

    override fun onCreate() {
        App.instance
            .getAppComponent()
            .createFirebaseMessagingItAmnesiaService()
            .inject(this)

        super.onCreate()
        presenter.view = this
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("4323", "Пришло")
        val content =message.data["message"]
        sharedPreference.accessToken?.let { Code(it) }?.let { presenter.sendUpdateAccessToken(it) }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun handleIntent(intent: Intent) {
        try {
            if (intent.extras != null) {
                val builder = RemoteMessage.Builder(MYFCM)
                for (key in (intent.extras ?: return).keySet()) {
                    builder.addData(key ?: return, (intent.extras ?: return)[key].toString())
                }
                onMessageReceived(builder.build())
            } else {
                super.handleIntent(intent)
            }
        } catch (e: Exception) {
            super.handleIntent(intent)
        }
    }
    companion object{
        var accessDenied = false
        private const val MYFCM = "MyFirebaseMessagingService"
    }
}