package com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging.FirebaseMessagingItAmnesiaService
import com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging.FirebaseMessagingItAmnesiaService.Companion.accessDenied
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfilePresenterImpl
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.profile.ProfileView
import com.example.sovkombank_team_challenge_it_amnezia.utils.navigateTo
import kotlinx.android.synthetic.main.profile_fragment.*
import kotlinx.coroutines.*
import javax.inject.Inject

class TransactionHistoryFragment : BaseFragment<TransactionHistoryPresenterImpl>(), TransactionHistoryView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createTransactionHistoryFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.transaction_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this

        if(accessDenied) waitAccess()
    }

    private fun getData(){
        //presenter.getUserInfo()
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
                sharedPreferences.adminMode = false
                sharedPreferences.pinCode = null
                accessDenied = false
                requireActivity().finish()
            }
            .onNegative { materialDialog, _ ->
                materialDialog.dismiss()
            }.show()
    }

    private fun waitAccess() {
        CoroutineScope(Dispatchers.IO).launch {
            delay(5000)
            CoroutineScope(Dispatchers.Main).launch {
                if(accessDenied){
                    waitAccess()
                    // добавить заглушку
                }
                else {
                    getData()
                    //убрать заглушку
                    coroutineContext.cancel()
                }
            }
        }
    }
}