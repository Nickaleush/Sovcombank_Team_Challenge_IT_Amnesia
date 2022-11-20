package com.example.sovkombank_team_challenge_it_amnezia.ui.client.predictionCurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.GetStatistics
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Prediction
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Statistics
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics.StatisticsFragment
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import kotlinx.android.synthetic.main.prediction_currency_fragment.*
import kotlinx.android.synthetic.main.statistics_fragment.*

class PredictionCurrencyFragment: BaseFragment<PredictionCurrencyPresenterImpl>(), PredictionCurrencyView {

    private var listPrices: MutableList<Double> = mutableListOf()

    private var currenciesList: java.util.ArrayList<String> = arrayListOf()

    override fun createComponent() {
        App.instance
            .getAppComponent()
            .createPredictionCurrencyFragment()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.prediction_currency_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        presenter.view = this
        toolbarPrediction.setNavigationOnClickListener { onBackPressed() }
        presenter.getAllCurrencies()
        generateGraphImageView.setOnClickListener {
            val selectedItem =  selectCurrencySpinnerStats.selectedItem.toString()
            currencyShortName = selectedItem.split("-")[0]
            currencyFullName = selectedItem.split("-")[1]
            presenter.getPrediction(
                Prediction(StatisticsFragment.currencyShortName)
            )
        }
    }

    override fun setupColumnGraph(prediction: MutableList<GetStatistics>){
        listPrices.clear()
        prediction.forEach {
            listPrices.add(it.value)
        }
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Columnrange)
            .title(resources.getString(R.string.Prediction))
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

    override fun showError(message: String?): Unit = Toast.makeText(requireContext(), getText(R.string.CheckCredentials), Toast.LENGTH_SHORT).show()

    override fun initCurrencyList(currencyList: ArrayList<Quotation>) {
        for (i in 0 until currencyList.size) {
            currenciesList.add(currencyList[i].currencyId + "-" + currencyList[i].currencyDto.fullName )
        }
        val dataAdapter = ArrayAdapter(requireContext(), R.layout.item_custom_currency_spinner, currenciesList)
        dataAdapter.setDropDownViewResource(R.layout.dropdown_currency_spinner_item)
        selectCurrencySpinnerStats.adapter = dataAdapter
    }

    override fun onBackPressed() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    companion object {
        var currencyShortName = ""
        var currencyFullName = ""
    }

}