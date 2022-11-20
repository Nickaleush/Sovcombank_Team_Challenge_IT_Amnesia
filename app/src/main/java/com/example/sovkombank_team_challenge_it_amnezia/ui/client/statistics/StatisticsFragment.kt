package com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.GetStatistics
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Statistics
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.*
import kotlinx.android.synthetic.main.statistics_fragment.*
import kotlinx.android.synthetic.main.transaction_history_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class StatisticsFragment : BaseFragment<StatisticsPresenterImpl>(), StatisticsView, DatePickerDialog.OnDateSetListener  {

    var listPrices: MutableList<Double> = mutableListOf()

    var listDate: MutableList<String> = mutableListOf()

    private var currenciesList: ArrayList<String> = arrayListOf()

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createStatisticsFragment()
            .inject(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.statistics_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        toolbarStatistics.setNavigationOnClickListener { onBackPressed() }
        presenter.getAllCurrencies()

        generateGraphicsImageView.setOnClickListener {
            val selectedItem =  selectCurrencySpinnerStats.selectedItem.toString()
            currencyShortName = selectedItem.split("-")[0]
            currencyFullName = selectedItem.split("-")[1]
            presenter.getStatistics(Statistics(currencyShortName, SEND_TO_DATE_FORMAT_STATS,SEND_FROM_DATE_FORMAT_STATS))
        }

        chipFromStats.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, yearStats, monthStats, dayStats).show()
            DATE_FROM_SELECTED_STATS = true
        }

        chipTillStats.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(requireContext(), this, yearStats, monthStats, dayStats).show()
            DATE_FROM_SELECTED_STATS = false
        }

    }

    override fun initCurrencyList(currencyList: ArrayList<Quotation>) {
        for (i in 0 until currencyList.size) {
            currenciesList.add(currencyList[i].currencyId + "-" + currencyList[i].currencyDto.fullName )
        }
        val dataAdapter = ArrayAdapter(requireContext(), R.layout.item_custom_currency_spinner, currenciesList)
        dataAdapter.setDropDownViewResource(R.layout.dropdown_currency_spinner_item)
        selectCurrencySpinnerStats.adapter = dataAdapter
    }

    override fun setupColumnGraph(statistic: MutableList<GetStatistics>){
        listPrices.clear()
        listDate.clear()
        statistic.forEach {
            listDate.add(it.date)
            listPrices.add(it.value)
        }
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Line)
            .title(resources.getString(R.string.GraphicsQuotations))
            .subtitle(currencyFullName)
            .backgroundColor(resources.getColor(R.color.white,null))
            .dataLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name(currencyFullName)
                    .data(listPrices.toTypedArray()),)
            )
        aa_chart_view.aa_drawChartWithChartModel(aaChartModel)
    }


    @SuppressLint("SimpleDateFormat")
    private fun getDateTimeCalendar() {
        val cal: Calendar = Calendar.getInstance()
        dayStats = cal.get(Calendar.DAY_OF_MONTH)
        monthStats = cal.get(Calendar.MONTH)
        yearStats = cal.get(Calendar.YEAR)
    }

    @SuppressLint("SimpleDateFormat")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDayStats = dayOfMonth
        savedMonthStats = month+1
        savedYearStats = year
        getDateTimeCalendar()
        resultMonthStats = if (savedMonthStats <10) "0${savedMonthStats}" else "$savedMonthStats"
        resultDayStats = if(savedDayStats <10) "0${savedDayStats}" else "$savedDayStats"
        dateTimeStats = "${savedYearStats}-${savedMonthStats}-${savedDayStats}"
        val outputFormat= SimpleDateFormat("dd MMMM yyyy")
        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = inputFormat.parse(dateTimeStats)
        val formattedDate = outputFormat.format(date)
        if (DATE_FROM_SELECTED_STATS) {
            chipFromStats.text = formattedDate
            SEND_FROM_DATE_FORMAT_STATS = inputFormat.format(date)
            DATE_FROM_SELECTED_STATS = false
        }
        else {
            chipTillStats.text = formattedDate
            SEND_TO_DATE_FORMAT_STATS = inputFormat.format(date)
            DATE_FROM_SELECTED_STATS = false
        }
    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), getText(R.string.CheckCredentials), Toast.LENGTH_SHORT).show()

    companion object {
        var currencyShortName = ""
        var currencyFullName = ""
        var DATE_FROM_SELECTED_STATS = false
        var yearStats = 0
        var monthStats = 0
        var dayStats = 0
        var savedYearStats = 0
        var savedMonthStats = 0
        var savedDayStats = 0
        var resultMonthStats = ""
        var resultDayStats = ""
        var dateTimeStats = ""
        var SEND_FROM_DATE_FORMAT_STATS = ""
        var SEND_TO_DATE_FORMAT_STATS = ""
    }
}