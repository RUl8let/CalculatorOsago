package com.rul8let.osagocalculator.ui.adapter.input

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.InputInfoItemBinding
import com.rul8let.osagocalculator.ui.InfoInputEnum
import com.rul8let.osagocalculator.ui.model.InputInfoItem

class InInputInfoViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    private val binding = InputInfoItemBinding.bind(itemView)

    fun bind(item: InputInfoItem, clickInputItem: (InfoInputEnum) -> Unit) {
        val hintVisible = item.texts.isNotEmpty()
        binding.hintUp.isVisible = hintVisible

        if (hintVisible){
            binding.enteredText.text = item.texts
            binding.hintUp.text = itemView.resources.getText(item.type.stringResId)
            binding.enteredText.gravity = Gravity.TOP
        } else {
            binding.enteredText.hint = itemView.resources.getText(item.type.stringResId)
            binding.enteredText.gravity = Gravity.CENTER_VERTICAL
        }

        binding.cardInput.setOnClickListener {
            clickInputItem(item.type)
        }
    }

    companion object{
        fun create(parent: ViewGroup): InInputInfoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.input_info_item,parent,false)
            return InInputInfoViewHolder(view)
        }
    }
}