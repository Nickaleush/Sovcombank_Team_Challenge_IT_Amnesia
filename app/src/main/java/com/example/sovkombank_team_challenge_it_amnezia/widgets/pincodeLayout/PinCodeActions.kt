package com.example.sovkombank_team_challenge_it_amnezia.widgets.pincodeLayout

interface PinCodeActions {
	fun onPinEntered(pin: String)
	fun onPinCleared()
	fun onPinFilled()
}