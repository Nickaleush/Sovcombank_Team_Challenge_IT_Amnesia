package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.createCode

import android.os.Bundle
import android.os.Handler
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.fragment.findNavController
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome.WelcomeFragment.Companion.AUTH_AS_ADMIN
import com.example.sovkombank_team_challenge_it_amnezia.utils.fadeIn
import com.example.sovkombank_team_challenge_it_amnezia.widgets.pincodeLayout.PinCodeActions
import kotlinx.android.synthetic.main.create_code_fragment.*
import javax.inject.Inject

class CreateCodeFragment: BaseFragment<CreateCodePresenterImpl>(), CreateCodeView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var firstCode: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.create_code_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this

        toolbarCreateCode.setNavigationOnClickListener {
            onBackPressed()
        }
        pincodeLayout_first.setCallback(firstCallback)

        buttonNextCreateCode.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.EmptyCode), Toast.LENGTH_SHORT).show()
        }
    }


    private val firstCallback: PinCodeActions = object : PinCodeActions {

        override fun onPinEntered(pin: String) {
            buttonNextCreateCode.setOnClickListener {
                sharedPreferences.pinCode = pin
                if (AUTH_AS_ADMIN) findNavController().navigate(R.id.action_createCodeFragment_to_clientListTabLayoutFragment)
                else findNavController().navigate(R.id.action_createCodeFragment_to_profileFragment)
            }
        }

        override fun onPinCleared() {
        }

        override fun onPinFilled() {
        }
    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createCreateCodeFragment()
            .inject(this)
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

}