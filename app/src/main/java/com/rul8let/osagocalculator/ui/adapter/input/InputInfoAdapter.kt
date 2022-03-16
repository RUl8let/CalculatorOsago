package com.rul8let.osagocalculator.ui.adapter.input

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rul8let.osagocalculator.ui.InfoInputEnum
import com.rul8let.osagocalculator.ui.model.InputInfoItem

class InputInfoAdapter(private val clickInputItem: (InfoInputEnum) -> Unit)
    : ListAdapter<InputInfoItem, InInputInfoViewHolder>(InputInfoCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InInputInfoViewHolder {
        return InInputInfoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: InInputInfoViewHolder, position: Int) {
        holder.bind(getItem(position), clickInputItem)
    }

    object InputInfoCallback : DiffUtil.ItemCallback<InputInfoItem>(){
        override fun areItemsTheSame(oldItem: InputInfoItem, newItem: InputInfoItem): Boolean {
            return oldItem.type==newItem.type
        }

        override fun areContentsTheSame(oldItem: InputInfoItem, newItem: InputInfoItem): Boolean {
            return oldItem.texts==newItem.texts
        }
    }
}