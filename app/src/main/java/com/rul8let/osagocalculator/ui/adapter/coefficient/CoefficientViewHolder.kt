package com.rul8let.osagocalculator.ui.adapter.coefficient

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.CoefficientInfoItemBinding
import com.rul8let.osagocalculator.ui.model.CoefficientItem

class CoefficientViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    private val binding = CoefficientInfoItemBinding.bind(itemView)

    fun bind(data : CoefficientItem) {
        val (abbreviation, decoding, Info, coefficient) = itemView.resources.getStringArray(data.idArrayString)
        binding.textAbbreviation.text = abbreviation
        binding.textDecoding.text = decoding
        binding.textInfo.text = Info
        //Проверяет есть ли личный коэффициент, если нет пишет базовый
        binding.valueCoefficient.text = data.coefficient.ifEmpty { coefficient }
    }

    companion object{
        fun create(parent : ViewGroup): CoefficientViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view= layoutInflater.inflate(R.layout.coefficient_info_item,parent,false)
            return CoefficientViewHolder(view)
        }
    }
}