package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.*
import com.example.sovkombank_team_challenge_it_amnezia.domain.sharedPreferences.SharedPreferences
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.services.firebaseMessaging.FirebaseMessagingItAmnesiaService.Companion.accessDenied
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottomsheet_create_new_account.*
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.*
import java.math.BigDecimal
import java.util.*
import java.util.UUID.randomUUID
import javax.inject.Inject
import kotlin.collections.ArrayList

class HomeFragment: BaseFragment<HomePresenterImpl>(), HomeView {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private lateinit var skeletonCurrency: Skeleton

    private lateinit var skeletonAccounts: Skeleton

    private var homeButtonItems: ArrayList<ListItemButton> = ArrayList()

    private lateinit var homeButtonsAdapter: HomeButtonsAdapter

    private lateinit var sheetView: View

    private lateinit var bottomSheetDialog: BottomSheetDialog

    private lateinit var amountAddMoneyEditText: EditText

    private lateinit var amountDeleteMoneyEditText: EditText

    private lateinit var sellCurrencyEditText: EditText

    private lateinit var amountBuyCurrencyEditText: EditText

    private lateinit var spinnerCurrency: Spinner

    private lateinit var selectAccountSpinner: Spinner

    private lateinit var buttonSaveNewAccount: Button

    private lateinit var buttonAddMoney: Button

    private lateinit var buttonDeleteMoneyFromAccount: Button

    private lateinit var buttonSellMoney: Button

    private lateinit var  buttonBuyCurrency: Button

    private lateinit var homeAccountsAdapter: HomeAccountsAdapter

    private var currenciesList: ArrayList<String> = arrayListOf()

    private var accountsSpinnerList: ArrayList<Account> = arrayListOf()

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createHomeFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        nameTextView.text = sharedPreferences.userName
        recyclerViewHomeButtons.layoutManager =  LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        homeButtonsAdapter = HomeButtonsAdapter(homeButtonItems, this@HomeFragment,requireContext())
        recyclerViewHomeButtons.adapter = homeButtonsAdapter
        initializeData()
        linearLayout_profile_from_home.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }
        skeletonCurrency = recyclerViewCurrency.applySkeleton(R.layout.item_home_currency)
        skeletonCurrency.maskCornerRadius = 30F
        skeletonCurrency.shimmerColor = requireActivity().getColor(R.color.blue)
        skeletonCurrency.showSkeleton()

        skeletonAccounts = recyclerViewHomeAccounts.applySkeleton(R.layout.item_home_account)
        skeletonAccounts.maskCornerRadius = 30F
        skeletonAccounts.shimmerColor = requireActivity().getColor(R.color.blue)
        skeletonAccounts.showSkeleton()

        getData()
        if (accessDenied) {
            hideMain()
            waitAccess()
        }
    }

    private fun getData() {
        presenter.getAllUserAccount()
        presenter.getAllCurrencies()
    }

    override fun initCurrenciesRecyclerView(currencyList: ArrayList<Quotation>) {
        if (this.isVisible) {
            recyclerViewCurrency.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            val adapter = HomeCurrencyAdapter(currencyList)
            recyclerViewCurrency.adapter = adapter
            adapter.setOnClickRecyclerListener(object : HomeCurrencyAdapter.OnClickListener {
                @SuppressLint("SimpleDateFormat")
                override fun onClick(position: Int) {
                    for (i in 0 until accountsSpinnerList.size) {
                        ACCOUNT_OPENED =
                            accountsSpinnerList[i].currency.name == currencyList[position].currencyDto.name
                        ACCOUNT_OPENED_POSITION = i
                        if (ACCOUNT_OPENED) break
                    }
                    if (ACCOUNT_OPENED) openBuyCurrencySheet(accountsSpinnerList[ACCOUNT_OPENED_POSITION].id)
                    else {
                        Toast.makeText(
                            requireContext(),
                            getText(R.string.CreateAccountFirst),
                            Toast.LENGTH_SHORT
                        ).show()
                        openCreateAccountSheet()
                    }
                }
            })
        }
    }

    override fun initAccountsRecyclerView(accountsList: ArrayList<Account>) {
        if (this.isVisible) {
            accountsSpinnerList.clear()
            val sortedList = accountsList.sortedWith(
                compareBy({ it.currency.name == "RUB" },
                    { it.currency.name })
            )
            for (i in 0 until sortedList.size - 1) accountsSpinnerList.add(sortedList[i])
            val reversedSortedList = sortedList.reversed()
            recyclerViewHomeAccounts.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            homeAccountsAdapter = HomeAccountsAdapter(reversedSortedList, this)
            recyclerViewHomeAccounts.adapter = homeAccountsAdapter
        }
    }

    override fun hideSkeleton() {
        skeletonCurrency.showOriginal()
    }

    override fun initCurrencyList(currencyList: ArrayList<Quotation>) {
        if (this.isVisible) {
            for (i in 0 until currencyList.size) {
                currenciesList.add(currencyList[i].currencyDto.name + "-" + currencyList[i].currencyDto.fullName)
            }
        }
    }

    override fun dismissBottomSheet() {
        bottomSheetDialog.dismiss()
    }

    private fun chooseCurrency() {
        val dataAdapter = ArrayAdapter(requireContext(), R.layout.item_custom_currency_spinner, currenciesList)
        dataAdapter.setDropDownViewResource(R.layout.dropdown_currency_spinner_item)
        spinnerCurrency.adapter = dataAdapter
    }

    private fun chooseAccount() {
        val dataAdapter = CustomSpinnerAdapter(requireContext(), accountsSpinnerList)
        selectAccountSpinner.adapter = dataAdapter
    }

    private fun initializeData() {
        homeButtonItems.clear()
        homeButtonItems.add(ListItemButton(HomeButtonType.CreateAccount))
        homeButtonItems.add(ListItemButton(HomeButtonType.AddMoney))
        homeButtonItems.add(ListItemButton(HomeButtonType.Withdraw))
        homeButtonItems.add(ListItemButton(HomeButtonType.Sell))
        homeButtonItems.add(ListItemButton(HomeButtonType.Statistics))
        homeButtonItems.add(ListItemButton(HomeButtonType.Prediction))
    }

    fun openCreateAccountSheet() {
        sheetView = requireActivity().layoutInflater.inflate(R.layout.bottomsheet_create_new_account, null)
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(sheetView)
        bottomSheetDialog.show()
        val mBehavior = BottomSheetBehavior.from(sheetView.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        spinnerCurrency = sheetView.findViewById(R.id.selectCurrencySpinner)
        buttonSaveNewAccount = sheetView.findViewById(R.id.buttonSaveNewAccount)
        chooseCurrency()
        buttonSaveNewAccount.setOnClickListener {
           val selectedItem =  spinnerCurrency.selectedItem.toString()
           val currencyShortName = selectedItem.split("-")[0]
            presenter.createNewAccount(CurrencyName(currencyShortName))
        }
    }

    fun openAddMoneySheet() {
        sheetView = requireActivity().layoutInflater.inflate(R.layout.bottomsheet_add_money_to_account, null)
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(sheetView)
        bottomSheetDialog.show()
        val mBehavior = BottomSheetBehavior.from(sheetView.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        amountAddMoneyEditText = sheetView.findViewById(R.id.amountAddMoneyEditText)
        buttonAddMoney = sheetView.findViewById(R.id.buttonAddMoney)
        buttonAddMoney.setOnClickListener {
            val amount = amountAddMoneyEditText.text.toString()
            val bigDecimalAmount = amount.toBigDecimal()
            val sendAmount = bigDecimalAmount.multiply(BigDecimal(100))
            presenter.addMoneyToAccount(AccountOperation(ACCOUNT_ID_RUB, sendAmount))
        }
    }

    fun openDeleteMoneySheet() {
        sheetView = requireActivity().layoutInflater.inflate(R.layout.bottomsheet_delete_money_from_account, null)
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(sheetView)
        bottomSheetDialog.show()
        val mBehavior = BottomSheetBehavior.from(sheetView.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        amountDeleteMoneyEditText = sheetView.findViewById(R.id.amountDeleteMoneyEditText)
        buttonDeleteMoneyFromAccount = sheetView.findViewById(R.id.buttonDeleteMoneyFromAccount)
        buttonDeleteMoneyFromAccount.setOnClickListener {
            val amount = amountDeleteMoneyEditText.text.toString()
            val bigDecimalAmount = amount.toBigDecimal()
            val deleteAmount = bigDecimalAmount.multiply(BigDecimal(100))
            presenter.deleteMoneyFromAccount(AccountOperation(ACCOUNT_ID_RUB, deleteAmount))
        }
    }

   override fun showSellBottomSheet() {
        sheetView = requireActivity().layoutInflater.inflate(R.layout.bottomsheet_sell_money_from_account, null)
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(sheetView)
        bottomSheetDialog.show()
        val mBehavior = BottomSheetBehavior.from(sheetView.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        selectAccountSpinner = sheetView.findViewById(R.id.selectAccountSpinner)
        sellCurrencyEditText = sheetView.findViewById(R.id.sellCurrencyEditText)
        buttonSellMoney = sheetView.findViewById(R.id.buttonSellMoney)
        chooseAccount()
        buttonSellMoney.setOnClickListener {
            val selected: Account = selectAccountSpinner.selectedItem as Account
            val amount = sellCurrencyEditText.text.toString()
            val bigDecimalAmount = amount.toBigDecimal()
            val sellAmount = bigDecimalAmount.multiply(BigDecimal(100))
            presenter.createSellTransaction(Transaction(sellAmount, ACCOUNT_ID_RUB, selected.id))
            SELL_OPENED = false
        }
    }

    fun openPredictionFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_predictionCurrencyFragment)
    }

    override fun showToastSuccess() {
        Toast.makeText(requireContext(),getText(R.string.OperationSuccess), Toast.LENGTH_SHORT).show()
    }

    fun openBuyCurrencySheet(id: UUID) {
        sheetView = requireActivity().layoutInflater.inflate(R.layout.bottomsheet_buy_currency, null)
        bottomSheetDialog = BottomSheetDialog(requireActivity(), R.style.CustomBottomSheetDialogTheme)
        bottomSheetDialog.setContentView(sheetView)
        bottomSheetDialog.show()
        val mBehavior = BottomSheetBehavior.from(sheetView.parent as View)
        mBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        amountBuyCurrencyEditText = sheetView.findViewById(R.id.amountBuyCurrencyEditText)
        buttonBuyCurrency = sheetView.findViewById(R.id.buttonBuyCurrency)
        buttonBuyCurrency.setOnClickListener {
        val amount = amountBuyCurrencyEditText.text.toString()
        val bigDecimalAmount = amount.toBigDecimal()
        val sellAmount = bigDecimalAmount.multiply(BigDecimal(100))
        presenter.createBuyTransaction(Transaction(sellAmount, id, ACCOUNT_ID_RUB))
        }
    }

    fun openStatisticsFragment() {
        findNavController().navigate(R.id.action_homeFragment_to_statisticsFragment)
    }

    private fun hideMain() {
        yourAccountsTextView.visibility = View.GONE
        waitUntilPermissionImageView.visibility = View.VISIBLE
        waitUntilConfirmTextView.visibility = View.VISIBLE
        linearLayout_profile_from_home.visibility = View.GONE
        recyclerViewHomeButtons.visibility = View.GONE
        recyclerViewHomeAccounts.visibility = View.GONE
        currencyTextView.visibility = View.GONE
        recyclerViewCurrency.visibility = View.GONE
    }

    private fun showMain() {
        waitUntilPermissionImageView.visibility = View.GONE
        waitUntilConfirmTextView.visibility = View.GONE
        linearLayout_profile_from_home.visibility = View.VISIBLE
        recyclerViewHomeButtons.visibility = View.VISIBLE
        recyclerViewHomeAccounts.visibility = View.VISIBLE
        currencyTextView.visibility = View.VISIBLE
        recyclerViewCurrency.visibility = View.VISIBLE
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
                if(accessDenied) {
                    waitAccess()
                }
                else {
                    getData()
                    accessDenied = false
                    showMain()
                    coroutineContext.cancel()
                }
            }
        }
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), getString(R.string.SomethingWentWrong), Toast.LENGTH_SHORT).show()

    companion object {
        var ACCOUNT_ID_RUB: UUID = randomUUID()
        var ACCOUNT_OPENED = false
        var ACCOUNT_OPENED_POSITION = 0
        var SELL_OPENED = false
    }
}