package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Account
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeFragment.Companion.ACCOUNT_ID_RUB
import kotlinx.android.synthetic.main.item_home_account.view.*
import java.math.BigDecimal
import java.math.RoundingMode

class HomeAccountsAdapter(private val accountsList: List<Account>, val fragment:HomeFragment ) : RecyclerView.Adapter<HomeAccountsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_account, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = accountsList[position]
        v.currencySymbolTextView.visibility = View.VISIBLE
        v.accountTypeTextview.visibility = View.VISIBLE
        if (item.currency.name == "RUB" ) ACCOUNT_ID_RUB = item.id
        val accountNumber = item.accountNumber
        val shortAccountNumber = accountNumber.substring(14)
        val amount = item.amount
        val balance = amount.divide(BigDecimal(100.0), 2,  RoundingMode.FLOOR)
        v.accountBalanceTextView.text = balance.toString()
        when (item.currency.name) {
            "RUB" -> {
                ACCOUNT_ID_RUB = item.id
                v.currencySymbolTextView.text = v.context.getText(R.string.Ruble)
            }
            "EUR" -> v.currencySymbolTextView.text = v.context.getText(R.string.Euro)
            "USD" -> v.currencySymbolTextView.text = v.context.getText(R.string.Dollar)
            "CNY" -> v.currencySymbolTextView.text = v.context.getText(R.string.Yuan)
            "GBP" -> v.currencySymbolTextView.text = v.context.getText(R.string.Sterling)
            else -> v.currencySymbolTextView.text = null
        }
        v.accountTypeTextview.text = item.currency.name
        v.billLast4digits.text = shortAccountNumber
    }

    override fun getItemCount() = accountsList.size
}
