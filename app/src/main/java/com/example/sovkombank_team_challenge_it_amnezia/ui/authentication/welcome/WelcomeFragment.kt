package com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.pager.AuthPagerAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.jakewharton.rxbinding.widget.RxTextView
import kotlinx.android.synthetic.main.auth_flow.*
import kotlinx.android.synthetic.main.welcome_fragment.*
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class WelcomeFragment: BaseFragment<WelcomePresenterImpl>(), WelcomeView {


    private lateinit var sheetView: View

    private lateinit var mBottomSheetDialog: BottomSheetDialog

    private lateinit var logInView : LinearLayout

    private lateinit var registrationView : LinearLayout

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createWelcomeFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.welcome_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        enterBank_cardView.setOnClickListener {
            showRedirectDialog()
        }
    }

    private fun showRedirectDialog() {
        sheetView = requireActivity().layoutInflater.inflate(R.layout.redirection_bottomsheet, null)
        mBottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.show()
        val mBehavior = BottomSheetBehavior.from(sheetView.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        logInView = sheetView.findViewById(R.id.liner_enter_bank)
        registrationView = sheetView.findViewById(R.id.linear_register)
        logInView.setOnClickListener {
            mBottomSheetDialog.dismiss()
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }
        registrationView.setOnClickListener {
            mBottomSheetDialog.dismiss()
            findNavController().navigate(R.id.action_welcomeFragment_to_registrationFragment)
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