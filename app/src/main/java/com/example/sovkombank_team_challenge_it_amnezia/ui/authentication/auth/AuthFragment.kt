package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.auth

import android.annotation.SuppressLint
import android.hardware.fingerprint.FingerprintManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration.RegistrationFragment
import com.example.sovkombank_team_challenge_it_amnezia.utils.navigateTo
import com.example.sovkombank_team_challenge_it_amnezia.utils.safeNavigate
import com.multidots.fingerprintauth.FingerPrintAuthCallback
import com.multidots.fingerprintauth.FingerPrintAuthHelper
import kotlinx.android.synthetic.main.auth_flow.*
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.auth_fragment.pinView
import kotlinx.android.synthetic.main.pin_view.*
import javax.inject.Inject

class AuthFragment: BaseFragment<AuthPresenterImpl>(), AuthView, FingerPrintAuthCallback {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    lateinit var fingerprintHelper: FingerPrintAuthHelper

    var isNavigating = false

    override fun createComponent() {
            App.instance
            .getAppComponent()
            .createAuthFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        fingerprintHelper = FingerPrintAuthHelper.getHelper(requireContext(), this)
        helloTextView.text = resources.getString(R.string.GoodMorning, sharedPreferences.userName)
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        pinView.setOnPinKeyClickListener = { keyPressed ->
            if (keyPressed == "fingerprint") fingerprintHelper.startAuth()
        }
        pinView.setOnCompletedListener = { pinCode ->
            when {
                (pinCode == sharedPreferences.pinCode && sharedPreferences.adminMode) -> {
                    isNavigating = true
                    findNavController().navigateTo(findNavController(),
                        R.id.action_authFragment_to_clientListTabLayoutFragment, true)
                }
                (pinCode == sharedPreferences.pinCode && !sharedPreferences.adminMode) -> {
                    isNavigating = true
                findNavController().navigateTo(findNavController(),
                    R.id.action_authFragment_to_homeFragment, true)
                }
                else ->   pinView.showError(true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        isNavigating = false
        fingerprintHelper.startAuth()
    }

    override fun onPause() {
        super.onPause()
        fingerprintHelper.stopAuth()
    }

    @SuppressLint("SetTextI18n")
    override fun onNoFingerPrintHardwareFound() {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.FingerprintNotSupported), Toast.LENGTH_SHORT).show()
    }

    override fun onAuthFailed(errorCode: Int, errorMessage: String?) {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.AuthFailed), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onNoFingerPrintRegistered() {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.NoFingerprintRegistered), Toast.LENGTH_SHORT).show()
    }

    @SuppressLint("SetTextI18n")
    override fun onBelowMarshmallow() {
        if (!isNavigating) Toast.makeText(requireContext(), getString(R.string.FingerprintNotAvailableOnThisOS), Toast.LENGTH_SHORT).show()
    }

    override fun onAuthSuccess(cryptoObject: FingerprintManager.CryptoObject?) {
        Toast.makeText(requireContext(), getString(R.string.AuthenticationSuccess), Toast.LENGTH_SHORT).show()
        if(sharedPreferences.adminMode){
            isNavigating = true
            findNavController().navigateTo(findNavController(),
                R.id.action_authFragment_to_clientListTabLayoutFragment, true)
        }
        else{
            isNavigating = true
            findNavController().navigateTo(findNavController(),
                R.id.action_authFragment_to_homeFragment, true)
        }
    }

    override fun onBackPressed() {
        MaterialDialog.Builder(requireContext())
            .content(getString(R.string.ExitConfirm))
            .positiveText(R.string.Yes)
            .negativeText(R.string.No)
            .contentColor(resources.getColor(R.color.black, null))
            .positiveColor(resources.getColor(R.color.blue, null))
            .negativeColor(resources.getColor(R.color.red, null))
            .onPositive { materialDialog, _ ->
                materialDialog.dismiss()
                requireActivity().finish()
            }
            .onNegative { materialDialog, _ ->
                materialDialog.dismiss()
            }.show()
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}