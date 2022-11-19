package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import kotlinx.android.synthetic.main.item_home_currency.view.*
import java.math.BigDecimal
import kotlin.collections.ArrayList

class HomeCurrencyAdapter(private val purchaseList: ArrayList<Quotation> ) : RecyclerView.Adapter<HomeCurrencyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_currency, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = purchaseList[position]
        v.rubleTextView.visibility = View.VISIBLE
        v.shortNameTextView.text = item.currencyDto.name
        v.currencyNameTextView.text = item.currencyDto.fullName
//        val value = item.value
//        val nominal = item.nominal
//        val bigDecimalObject = BigDecimal(value)
        v.currencyCostTextView.text = item.value
        v.rubleTextView.text = v.context.getText(R.string.Ruble)
    }

    override fun getItemCount() = purchaseList.size
}