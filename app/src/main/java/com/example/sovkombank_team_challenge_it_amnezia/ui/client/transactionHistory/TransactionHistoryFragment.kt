package com.example.sovkombank_team_challenge_it_amnezia.ui.client.transactionHistory

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.TransactionDTO
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging.FirebaseMessagingItAmnesiaService.Companion.accessDenied
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration.RegistrationFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.authentication.registration.RegistrationFragment.Companion.dateTime
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.registration_fragment.*
import kotlinx.android.synthetic.main.transaction_history_fragment.*
import kotlinx.android.synthetic.main.transaction_history_item.view.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

class TransactionHistoryFragment : BaseFragment<TransactionHistoryPresenterImpl>(),
    TransactionHistoryView, DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    var transactionHistoryList: MutableList<TransactionDTO> = mutableListOf()
    var initialTransactionHistoryList: MutableList<TransactionDTO> = mutableListOf()

    lateinit var adapter: TransactionHistoryAdapter

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createTransactionHistoryFragment()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.transaction_history_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        getData()
        DATA_FROM_FILTER_SELECTED = false
        DATA_TO_FILTER_SELECTED = false
        if (accessDenied){
            waitAccess()
            hideMain()
        }
        chip_group_choice.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            chip?.let { chipView ->
                chipSelected = chip.text as String
                filterList()
            } ?: kotlin.run {
            }
        }

        chipFrom.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, yearHistory, monthHistory, dayHistory).show()
            DATE_FROM_SELECTED = true
        }

        chipTill.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, yearHistory, monthHistory, dayHistory).show()
            DATE_FROM_SELECTED = false
        }
    }

    private fun getData() {
        presenter.getTransactionHistory()
    }

    private fun filterList() {
        transactionHistoryList = initialTransactionHistoryList
        when (chipSelected) {
            getString(R.string.Recharge) -> chipSelected = "RECHARGE"
            getString(R.string.Transaction) -> chipSelected = "TRANSACTION"
            getString(R.string.Withdrawal) -> chipSelected = "WITHDRAWAL"
            else -> {chipSelected= "ALL"}
        }
        adapter.filter.filter(chipSelected)
    }

    private fun hideMain(){
        waitUntilAccessImageView.visibility = View.VISIBLE
        waitUntilConfirmationTextView.visibility = View.VISIBLE
        toolbarTransactionHistoryTabLayout.visibility = View.GONE
        chip_group_date.visibility = View.GONE
        chip_group_choice.visibility = View.GONE
        transactionHistoryRecyclerView.visibility = View.GONE
        transactionHistoryListTextView.visibility = View.GONE
        transactionHistoryListImageView.visibility = View.GONE
    }

    private fun showMain(){
        waitUntilAccessImageView.visibility = View.GONE
        waitUntilConfirmationTextView.visibility = View.GONE
        toolbarTransactionHistoryTabLayout.visibility = View.VISIBLE
        chip_group_date.visibility = View.VISIBLE
        chip_group_choice.visibility = View.VISIBLE
        transactionHistoryRecyclerView.visibility = View.VISIBLE
        transactionHistoryListTextView.visibility = View.VISIBLE
        transactionHistoryListImageView.visibility = View.VISIBLE
    }

    override fun initTransactionHistoryResycler(listTransactionHistory: MutableList<TransactionDTO>) {
        transactionHistoryList = listTransactionHistory
        initialTransactionHistoryList = listTransactionHistory
        transactionHistoryRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter = TransactionHistoryAdapter(transactionHistoryList)
        transactionHistoryRecyclerView.adapter = adapter
        filterList()
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
                if (accessDenied) {
                    waitAccess()
                } else {
                    getData()
                    showMain()
                    accessDenied = false
                    coroutineContext.cancel()
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        dayHistory = cal.get(Calendar.DAY_OF_MONTH)
        monthHistory = cal.get(Calendar.MONTH)
        yearHistory = cal.get(Calendar.YEAR)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SimpleDateFormat")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateTimeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        savedDayHistory = dayOfMonth
        savedMonthHistory = month+1
        savedYearHistory = year
        getDateTimeCalendar()
        resultMonthHistory = if (savedMonthHistory <10) "0${savedMonthHistory}" else "${savedMonthHistory}"
        resultDayHistory = if(savedDayHistory <10) "0${savedDayHistory}" else "$savedDayHistory"
        dateTimeHistory = "${savedYearHistory}-${savedMonthHistory}-${savedDayHistory}"
        val outputFormat= SimpleDateFormat("dd MMMM yyyy")
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = inputFormat.parse(dateTimeHistory)
        val formattedDate = outputFormat.format(date)
        if (DATE_FROM_SELECTED) {
            chipFrom.text = formattedDate
            SEND_FROM_DATE_FORMAT = inputFormat.format(date)
            DATE_FROM_SELECTED = false
            DATA_FROM_FILTER_SELECTED = true
            filterList()
        }
        else {
            chipTill.text = formattedDate
            SEND_TO_DATE_FORMAT = inputFormat.format(date)
            DATE_FROM_SELECTED = false
            DATA_TO_FILTER_SELECTED = true
            filterList()
        }
    }

    override fun showError(message: String?): Unit =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    companion object {
        var chipSelected = ""
        var DATE_FROM_SELECTED = false
        var DATA_FROM_FILTER_SELECTED = false
        var DATA_TO_FILTER_SELECTED = false
        var yearHistory = 0
        var monthHistory = 0
        var dayHistory = 0
        var savedYearHistory = 0
        var savedMonthHistory = 0
        var savedDayHistory = 0
        var resultMonthHistory = ""
        var resultDayHistory = ""
        var dateTimeHistory = ""
        var dateTimeNow = ""
        var SEND_FROM_DATE_FORMAT = ""
        var SEND_TO_DATE_FORMAT = ""
    }
}