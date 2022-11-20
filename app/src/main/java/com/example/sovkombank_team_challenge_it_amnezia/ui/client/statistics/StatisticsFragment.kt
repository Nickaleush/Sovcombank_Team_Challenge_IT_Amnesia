package com.example.sovkombank_team_challenge_it_amnezia.ui.client.statistics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.sovkombank_team_challenge_it_amnezia.App
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ClientDTO
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.GetStatistics
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Statistics
import com.example.sovkombank_team_challenge_it_amnezia.mvp.BaseFragment
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartModel
import com.github.aachartmodel.aainfographics.aachartcreator.AAChartType
import com.github.aachartmodel.aainfographics.aachartcreator.AASeriesElement
import com.github.aachartmodel.aainfographics.*
import kotlinx.android.synthetic.main.statistics_fragment.*
import java.time.LocalDate

class StatisticsFragment : BaseFragment<StatisticsPresenterImpl>(), StatisticsView {

    var listPrices: MutableList<Double> = mutableListOf()
    var listDate: MutableList<String> = mutableListOf()
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

        presenter.getStatistics(Statistics("2022-11-12","2022-11-28","RUB"))

    }

    override fun setupColumnGraph(statistic: MutableList<GetStatistics>){
        listPrices.clear()
        listDate.clear()
        statistic.forEach {
            listDate.add(it.date)
            listPrices.add(it.value)
        }
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Column)
            .title("title")
            .subtitle("subtitle")
            .backgroundColor("#4b2b7f")
            .dataLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name("Tokyo")
                    .data(listDate.toTypedArray()),
                AASeriesElement()
                    .name("NewYork")
                    .data(listPrices.toTypedArray()),
            )
            )
        aa_chart_view.aa_drawChartWithChartModel(aaChartModel)
    }
    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
    override fun showError(message: String?): Unit =
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}