package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.createCode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.widgets.pincodeLayout.PinCodeActions
import kotlinx.android.synthetic.main.create_code_fragment.*

class CreateCodeFragment: BaseFragment<CreateCodePresenterImpl>(), CreateCodeView {

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
        pincodeLayout.setCallback(callback)
    }

    private val callback: PinCodeActions = object : PinCodeActions {
        override fun onPinEntered(pin: String) {

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