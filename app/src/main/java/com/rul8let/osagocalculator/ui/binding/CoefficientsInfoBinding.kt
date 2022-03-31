package com.rul8let.osagocalculator.ui.binding

import android.os.Build
import android.text.Html
import androidx.core.view.isVisible
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.CoefficientsInfoBinding
import com.rul8let.osagocalculator.ui.adapter.coefficient.CoefficientInfoAdapter
import com.rul8let.osagocalculator.ui.model.CoefficientItem

fun CoefficientsInfoBinding.bindCoefficientCard(
    expandedCoefficientCard: LiveData<Boolean>,
    coefficientListData: LiveData<List<CoefficientItem>>,
    lifecycleOwner: LifecycleOwner,
    changeExpanded: (b : Boolean) -> Unit,
) {
    val adapter = CoefficientInfoAdapter()
    coefficientList.adapter = adapter
    coefficientList.layoutManager = LinearLayoutManager(root.context)
    coefficientList.isNestedScrollingEnabled = false

    expandedCoefficientCard.observe(lifecycleOwner){
        coefficientList.isVisible = it
    }

    coefficientListData.observe(lifecycleOwner){
        adapter.submitList(it)

        val text = coefficientList.context.getString(
            R.string.coefficient_formula,
            it[0].headerValue,
            it[1].headerValue,
            it[2].headerValue,
            it[3].headerValue,
            it[4].headerValue,
            it[5].headerValue
        )

        val styledText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(text)
        }
        formulaText.text = styledText
    }

    checkBoxExpand.setOnCheckedChangeListener { _, b ->
        changeExpanded(b)
    }
}