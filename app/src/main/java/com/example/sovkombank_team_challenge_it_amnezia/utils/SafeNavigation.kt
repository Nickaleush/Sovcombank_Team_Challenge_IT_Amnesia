package com.example.sovkombank_team_challenge_it_amnezia.utils

import android.app.Activity
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

fun NavController.safeNavigate(direction: NavDirections) {
        Log.d("9999", "Нажато")
        currentDestination?.getAction(direction.actionId)?.run {
            Log.d("9999", "Обработано")
            navigate(direction)
        }
    }

    fun NavController.navigateTo(navController: NavController,
                            destinationId: Int, needToClearBackStack: Boolean = false) {
        val options = if (needToClearBackStack) {
            NavOptions.Builder()
                .setPopUpTo(navController.graph.id, false)
                .build()
        } else null
        navController.navigate(resId = destinationId, args = null, navOptions = options)
    }