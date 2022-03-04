package com.rul8let.osagocalculator.ui.screen.calculator

import android.animation.LayoutTransition
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.CalculatorScreenBinding
import com.rul8let.osagocalculator.ui.InfoInputEnum
import com.rul8let.osagocalculator.ui.adapter.coefficient.CoefficientInfoAdapter
import com.rul8let.osagocalculator.ui.adapter.input.InputInfoAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculatorFragment : Fragment(){

    private var _binding : CalculatorScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CalculatorViewModel by hiltNavGraphViewModels(R.id.osago_nav)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CalculatorScreenBinding.inflate(inflater)
        binding.headerLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bindInfoCard()
        binding.bindAdapterInput()

        viewModel.enabledButton.observe(viewLifecycleOwner){
            binding.button.isEnabled = it
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_calculatorFragment_to_priceCalculationFragment)
        }

    }

    private fun CalculatorScreenBinding.bindInfoCard(){
        val adapter = CoefficientInfoAdapter()
        coefficientsInfo.coefficientList.adapter = adapter
        coefficientsInfo.coefficientList.layoutManager = LinearLayoutManager(root.context)
        coefficientsInfo.coefficientList.isNestedScrollingEnabled = false

        coefficientsInfo.checkBoxExpand.setOnCheckedChangeListener { _, b ->
            viewModel.changeExpanded(b)
        }

        viewModel.expanded.observe(viewLifecycleOwner){
            coefficientsInfo.coefficientList.isVisible = it
        }

        viewModel.coefficientList.observe(viewLifecycleOwner){
            adapter.submitList(it)
            val text = getString(
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
            coefficientsInfo.formulaText.text = styledText
        }
    }

    private fun CalculatorScreenBinding.bindAdapterInput(){
        val adapter = InputInfoAdapter(clickInputItem)
        inputList.adapter = adapter
        inputList.layoutManager = LinearLayoutManager(root.context)
        inputList.isNestedScrollingEnabled = false

        viewModel.inputInfoList.observe(viewLifecycleOwner){
            adapter.submitList(it)
        }
    }

    private val clickInputItem = fun (type : InfoInputEnum){
        viewModel.selectInputUpdate(type)
        findNavController().navigate(R.id.action_calculatorFragment_to_bottomInputFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}