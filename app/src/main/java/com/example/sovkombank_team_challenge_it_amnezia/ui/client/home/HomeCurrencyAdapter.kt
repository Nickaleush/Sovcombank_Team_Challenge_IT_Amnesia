package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.Quotation
import kotlinx.android.synthetic.main.bottomsheet_buy_currency.view.*
import kotlinx.android.synthetic.main.item_home_currency.view.*
import java.math.BigDecimal
import kotlin.collections.ArrayList

class HomeCurrencyAdapter(private val purchaseList: ArrayList<Quotation> ) : RecyclerView.Adapter<HomeCurrencyAdapter.ViewHolder>() {

    private lateinit var mListener: OnClickListener

    class ViewHolder(itemView: View, listener: OnClickListener) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.buyCurrencyTextViewBlue.setOnClickListener {
                listener.onClick(absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_currency, parent, false)
        return ViewHolder(view, mListener)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val v = holder.itemView
        val item = purchaseList[position]
        v.rubleTextView.visibility = View.VISIBLE
        v.shortNameTextView.text = item.currencyDto.name
        v.currencyNameTextView.text = item.currencyDto.fullName
        v.nominalTextView.text = item.nominal.toString()
        v.currencyCostTextView.text = item.value
        v.rubleTextView.text = v.context.getText(R.string.Ruble)
    }

    interface OnClickListener {
        fun onClick(position: Int)
    }

    fun setOnClickRecyclerListener(listener: OnClickListener){
        mListener = listener
    }

    override fun getItemCount() = purchaseList.size
}