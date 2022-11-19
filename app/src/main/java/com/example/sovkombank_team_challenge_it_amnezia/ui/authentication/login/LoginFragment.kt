package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.findNavController
import com.example.sovkombank_team_challenge_it_amnezia.widgets.countryPicker.CCPicker
import com.example.sovkombank_team_challenge_it_amnezia.widgets.countryPicker.adapter.CountryPickerAdapter
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.UserToLogin
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment.Companion.AUTH_AS_ADMIN
import com.example.sovkombank_team_challenge_it_amnezia.utils.InsetsWithKeyboardAnimationCallback
import com.example.sovkombank_team_challenge_it_amnezia.utils.InsetsWithKeyboardCallback
import com.example.sovkombank_team_challenge_it_amnezia.widgets.countryPicker.model.Country
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.registration_fragment.*
import javax.inject.Inject

class LoginFragment: BaseFragment<LoginPresenterImpl>(), LoginView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        toolbarLogin.setNavigationOnClickListener { onBackPressed() }

        countryCodeTextViewLogin.setOnClickListener {
            CCPicker.showPicker(
                requireActivity(),
                object : CountryPickerAdapter.OnCountrySelectedListener {
                    override fun onCountrySelected(country: Country?) {
                        val countryCode = country?.countryCode
                        countryCodeTextViewLogin.setText(countryCode)
                    }
                })
        }

        buttonNextLogin.setOnClickListener {
            val phoneNumber = phoneEditTextLogin.text.toString().replace("-", "")
            val finalPhoneNumber = phoneNumber.replace(" ", "")
            if (AUTH_AS_ADMIN) {
                presenter.loginAdmin(UserToLogin(
                        (countryCodeTextViewLogin.text.toString() + finalPhoneNumber),   passwordEditTextLogin.text.toString()))
            } else {
                    presenter.loginClient(UserToLogin(
                    (countryCodeTextViewLogin.text.toString() + finalPhoneNumber),   passwordEditTextLogin.text.toString()))
                }
        }

        val insetsWithKeyboardCallback = InsetsWithKeyboardCallback(requireActivity().window)
        ViewCompat.setOnApplyWindowInsetsListener(login_constraint_layout, insetsWithKeyboardCallback)
        ViewCompat.setWindowInsetsAnimationCallback(login_constraint_layout, insetsWithKeyboardCallback)

        val insetsWithKeyboardAnimationCallback = InsetsWithKeyboardAnimationCallback(buttonNextLogin)
        ViewCompat.setWindowInsetsAnimationCallback(buttonNextLogin, insetsWithKeyboardAnimationCallback)
    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createLoginFragment()
            .inject(this)
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), getString(R.string.CheckCredentials), Toast.LENGTH_SHORT).show()

    override fun navToMainFlow() {
        Toast.makeText(
            requireContext(),
            getString(R.string.AuthenticationSuccess),
            Toast.LENGTH_SHORT
        ).show()
        if (sharedPreferences.pinCode == null) {
            findNavController().navigate(R.id.action_loginFragment_to_createCodeFragment)
        } else {
            if (AUTH_AS_ADMIN) findNavController().navigate(R.id.action_loginFragment_to_clientListTabLayoutFragment)
            else findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
        }
    }

}