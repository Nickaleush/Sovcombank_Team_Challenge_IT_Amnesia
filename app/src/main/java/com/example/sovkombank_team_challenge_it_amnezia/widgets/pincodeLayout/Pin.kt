package com.example.sovkombank_team_challenge_it_amnezia.widgets.pincodeLayout

import android.content.Context
import android.view.View
import android.widget.ViewSwitcher
import com.example.sovkombank_team_challenge_it_amnezia.R

class Pin(context: Context?) : ViewSwitcher(context){

	init {
		View.inflate(context, R.layout.pin, this)
	}
}
