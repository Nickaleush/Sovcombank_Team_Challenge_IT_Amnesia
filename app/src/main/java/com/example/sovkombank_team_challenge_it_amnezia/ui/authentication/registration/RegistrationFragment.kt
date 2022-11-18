package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration

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
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.utils.InsetsWithKeyboardAnimationCallback
import com.example.sovkombank_team_challenge_it_amnezia.utils.InsetsWithKeyboardCallback
import com.example.sovkombank_team_challenge_it_amnezia.widgets.countryPicker.model.Country
import kotlinx.android.synthetic.main.registration_fragment.*

class RegistrationFragment : BaseFragment<RegistrationPresenterImpl>(), RegistrationView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.registration_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.view = this
        presenter.start()

        countryCodeTextView.setOnClickListener {
            CCPicker.showPicker(
                requireActivity(),
                object : CountryPickerAdapter.OnCountrySelectedListener {
                    override fun onCountrySelected(country: Country?) {
                        val countryCode = country?.countryCode
                        countryCodeTextView.setText(countryCode)
                    }
                })
        }
        buttonNextRegistration.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_createCodeFragment)
        }

        toolbarRegistration.setNavigationOnClickListener { onBackPressed() }

        val insetsWithKeyboardCallback = InsetsWithKeyboardCallback(requireActivity().window)
        ViewCompat.setOnApplyWindowInsetsListener(registration_constraint_layout, insetsWithKeyboardCallback)
        ViewCompat.setWindowInsetsAnimationCallback(registration_constraint_layout, insetsWithKeyboardCallback)

        val insetsWithKeyboardAnimationCallback = InsetsWithKeyboardAnimationCallback(buttonNextRegistration)
        ViewCompat.setWindowInsetsAnimationCallback(buttonNextRegistration, insetsWithKeyboardAnimationCallback)
    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createRegistrationFragment()
            .inject(this)
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}