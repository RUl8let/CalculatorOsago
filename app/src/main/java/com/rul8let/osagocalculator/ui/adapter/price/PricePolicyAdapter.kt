package com.rul8let.osagocalculator.ui.adapter.price

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.osagocalculator.ui.model.PriceCalculation
import com.rul8let.osagocalculator.ui.model.PriceCalculation.PriceCalculationItem

class PricePolicyAdapter :  ListAdapter<PriceCalculation, RecyclerView.ViewHolder>(PricePolicyDiffCallback) {

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            PriceCalculation.data->PricePolicyViewHolder.create(parent)
            else -> {
                PriceCalculationLoadViewHolder.create(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PricePolicyViewHolder -> holder.bind(getItem(position) as PriceCalculationItem)
        }
    }

    object PricePolicyDiffCallback : DiffUtil.ItemCallback<PriceCalculation>() {
        override fun areItemsTheSame(oldItem: PriceCalculation, newItem: PriceCalculation): Boolean {
            return oldItem.getType()==newItem.getType()
        }

        override fun areContentsTheSame(oldItem: PriceCalculation, newItem: PriceCalculation): Boolean {
            return oldItem==newItem
        }
    }
}