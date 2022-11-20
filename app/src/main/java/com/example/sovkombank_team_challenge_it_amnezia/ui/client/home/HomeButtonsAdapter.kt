package com.example.sovkombank_team_challenge_it_amnezia.ui.client.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sovkombank_team_challenge_it_amnezia.R
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.HomeButtonType
import com.example.sovkombank_team_challenge_it_amnezia.domain.models.ListItemButton
import com.example.sovkombank_team_challenge_it_amnezia.ui.client.home.HomeFragment.Companion.SELL_OPENED
import kotlinx.android.synthetic.main.item_home_button.view.*


class HomeButtonsAdapter(private val items: ArrayList<ListItemButton> = ArrayList(), val fragment: HomeFragment, context: Context) : RecyclerView.Adapter<HomeButtonsAdapter.ViewHolder>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(layoutInflater.inflate(HomeButtonType.values()[viewType].layoutRes, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (HomeButtonType.values()[items[position].type.ordinal]) {

            HomeButtonType.CreateAccount  ->  {
                holder.itemView.holderButton.setCardBackgroundColor(holder.itemView.context.getColor(R.color.red))
                holder.itemView.buttonName.setTextColor(holder.itemView.context.getColor(R.color.red))
                holder.itemView.buttonImage.setImageResource(R.drawable.create_new_account_icon_foreground)
                holder.itemView.buttonName.text = holder.itemView.context.getString(R.string.CreateAccount)
                holder.itemView.setOnClickListener { fragment.openCreateAccountSheet() }
            }

            HomeButtonType.AddMoney  ->  {
                holder.itemView.holderButton.setCardBackgroundColor(holder.itemView.context.getColor(R.color.white))
                holder.itemView.buttonName.setTextColor(holder.itemView.context.getColor(R.color.blue))
                holder.itemView.buttonImage.setImageResource(R.drawable.plus_icon_foreground)
                holder.itemView.buttonName.text = holder.itemView.context.getString(R.string.Deposit)
                holder.itemView.setOnClickListener { fragment.openAddMoneySheet() }
            }

            HomeButtonType.Withdraw -> {
                holder.itemView.holderButton.setCardBackgroundColor(holder.itemView.context.getColor(R.color.white))
                holder.itemView.buttonName.setTextColor(holder.itemView.context.getColor(R.color.blue))
                holder.itemView.buttonImage.setImageResource(R.drawable.withdraw_icon_foreground)
                holder.itemView.buttonName.text = holder.itemView.context.getString(R.string.WithDraw)
                holder.itemView.setOnClickListener { fragment.openDeleteMoneySheet() }
            }

            HomeButtonType.Sell -> {
                holder.itemView.holderButton.setCardBackgroundColor(holder.itemView.context.getColor(R.color.white))
                holder.itemView.buttonName.setTextColor(holder.itemView.context.getColor(R.color.blue))
                holder.itemView.buttonImage.setImageResource(R.drawable.sell_icon_foreground)
                holder.itemView.buttonName.text = holder.itemView.context.getString(R.string.Sell)
                holder.itemView.setOnClickListener {
                    SELL_OPENED = true
                    fragment.presenter.getAllUserAccount()
                }
            }

            HomeButtonType.Statistics ->    {
                holder.itemView.holderButton.setCardBackgroundColor(holder.itemView.context.getColor(R.color.white))
                holder.itemView.buttonName.setTextColor(holder.itemView.context.getColor(R.color.blue))
                holder.itemView.buttonImage.setImageResource(R.drawable.statistics_icon_foreground)
                holder.itemView.buttonName.text = holder.itemView.context.getString(R.string.Statistics)
                holder.itemView.setOnClickListener { fragment.openStatisticsFragment() }
            }

            HomeButtonType.Prediction -> {
                holder.itemView.holderButton.setCardBackgroundColor(holder.itemView.context.getColor(R.color.white))
                holder.itemView.buttonName.setTextColor(holder.itemView.context.getColor(R.color.blue))
                holder.itemView.buttonImage.setImageResource(R.drawable.prediction_icon_foreground)
                holder.itemView.buttonName.text = holder.itemView.context.getString(R.string.Prediction)
                holder.itemView.setOnClickListener { fragment.openPredictionFragment() }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].type.ordinal
    }

    override fun getItemCount(): Int {
        return items.size
    }
}