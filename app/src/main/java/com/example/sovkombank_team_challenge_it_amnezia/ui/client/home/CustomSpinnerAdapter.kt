package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Account
import kotlinx.android.synthetic.main.item_home_account.view.*
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.ArrayList

class CustomSpinnerAdapter(context: Context, accountList: ArrayList<Account>) : ArrayAdapter<Account>(context, 0, accountList) {
    val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: layoutInflater.inflate(R.layout.item_custom_account_spinner, parent, false)
        getItem(position)?.let {
            initView(position, view, parent)
        }
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: layoutInflater.inflate(R.layout.item_custom_account_spinner, parent, false)
        getItem(position)?.let {
            initView(position, view, parent)
        }
        return view
    }

    private fun initView(
        position: Int, convertView: View,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_custom_account_spinner, parent, false)
        }
        val billLast4digits = convertView.findViewById<TextView>(R.id.billLast4digitsSpinner)
        val accountBalanceTextViewSpinner = convertView.findViewById<TextView>(R.id.accountBalanceTextViewSpinner)
        val accountTypeTextview = convertView.findViewById<TextView>(R.id.accountTypeTextviewSpinner)
        val currencySymbolTextViewSpinner = convertView.findViewById<TextView>(R.id.currencySymbolTextViewSpinner)
        val currentItem: Account? = getItem(position)

        if (currentItem != null) {
            billLast4digits.text = currentItem.accountNumber.substring(14)
            val amount = currentItem.amount
            val balance = amount.divide(BigDecimal(100.0), 2,  RoundingMode.FLOOR)
            accountBalanceTextViewSpinner.text = balance.toString()
            accountTypeTextview.text = currentItem.currency.name
            when (currentItem.currency.name) {
                "RUB" -> currencySymbolTextViewSpinner.text = context.getText(R.string.Ruble)
                "EUR" -> currencySymbolTextViewSpinner.text = context.getText(R.string.Euro)
                "USD" -> currencySymbolTextViewSpinner.text = context.getText(R.string.Dollar)
                "CNY" -> currencySymbolTextViewSpinner.text = context.getText(R.string.Yuan)
                "GBP" ->currencySymbolTextViewSpinner.text = context.getText(R.string.Sterling)
                else -> currencySymbolTextViewSpinner.text = null
            }
        }
        return convertView
    }
}