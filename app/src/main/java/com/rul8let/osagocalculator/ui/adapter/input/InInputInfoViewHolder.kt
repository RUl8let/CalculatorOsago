package com.rul8let.osagocalculator.ui.adapter.input

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.InputInfoItemBinding
import com.rul8let.osagocalculator.ui.InfoInputEnum
import com.rul8let.osagocalculator.ui.model.InputInfoItem

class InInputInfoViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView){

    private val binding = InputInfoItemBinding.bind(itemView)

    fun bind(item: InputInfoItem, clickInputItem: (InfoInputEnum) -> Unit) {
        binding.inputText.hint = itemView.resources.getText(item.type.stringResId)
        // Если нет текста введенного пользователем, то отключается hint для того что бы отобразить больше одной строчки
        binding.layoutText.isHintEnabled = item.texts.isNotEmpty()
        binding.inputText.setText(item.texts)

        binding.inputText.setOnClickListener {
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