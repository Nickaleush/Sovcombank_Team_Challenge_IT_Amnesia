package com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.TransactionDTO
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging.FirebaseMessagingItAmnesiaService.Companion.accessDenied
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.transaction_history_fragment.*
import kotlinx.android.synthetic.main.transaction_history_item.view.*
import kotlinx.coroutines.*
import javax.inject.Inject

class TransactionHistoryFragment : BaseFragment<TransactionHistoryPresenterImpl>(), TransactionHistoryView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var transactionHistoryList: MutableList<TransactionDTO> = mutableListOf()

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
        getData()
        if(accessDenied) waitAccess()
        chip_group_choice.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let {chipView ->
                chipSelected = chip.text as String
                filterList(chipSelected)
            } ?: kotlin.run {
            }
        }
    }

    private fun getData(){
        presenter.getTransactionHistory()
    }

    private fun filterList(type: String){
        if(type!="All"){
            transactionHistoryList.filter {
                when (it.type) {
                    "RECHARGE" -> it.type = getString(R.string.Recharge)
                    "TRANSACTION" -> it.type = getString(R.string.Transaction)
                    "WITHDRAWAL" ->  it.type = getString(R.string.Withdrawal)
                    else -> {}
                }
                it.type == type
            }
            transactionHistoryRecyclerView.adapter?.notifyDataSetChanged()
        } else getData()

    }

    override fun initTransactionHistoryResycler(listTransactionHistory: MutableList<TransactionDTO>) {
        transactionHistoryList = listTransactionHistory
        transactionHistoryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val adapter = TransactionHistoryAdapter(transactionHistoryList)
        transactionHistoryRecyclerView.adapter = adapter
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

    companion object{
        var chipSelected = ""
    }
}