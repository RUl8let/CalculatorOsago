package com.rul8let.osagocalculator.ui.adapter.coefficient

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rul8let.osagocalculator.ui.model.CoefficientItem

class CoefficientInfoAdapter : ListAdapter<CoefficientItem, CoefficientViewHolder>(CoefficientDiffCallback){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoefficientViewHolder {
        return CoefficientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CoefficientViewHolder, position: Int) {
        holder.bind(getItem(position))
        Log.e("CoefficientInfoAdapter","${position}")
    }

    object CoefficientDiffCallback : DiffUtil.ItemCallback<CoefficientItem>() {
        override fun areItemsTheSame(oldItem: CoefficientItem, newItem: CoefficientItem): Boolean {
            return oldItem.idArrayString==newItem.idArrayString
        }

        override fun areContentsTheSame(oldItem: CoefficientItem, newItem: CoefficientItem): Boolean {
            return oldItem.coefficient==newItem.coefficient
        }
    }
}