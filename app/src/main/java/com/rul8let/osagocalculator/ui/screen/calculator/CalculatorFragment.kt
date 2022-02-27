package com.rul8let.osagocalculator.ui.screen.calculator

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rul8let.osagocalculator.R
import com.rul8let.osagocalculator.databinding.CalculatorScreenBinding
import com.rul8let.osagocalculator.ui.adapter.coefficient.CoefficientInfoAdapter
import com.rul8let.osagocalculator.ui.adapter.input.InputInfoAdapter

class CalculatorFragment : Fragment(){

    private var _binding : CalculatorScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CalculatorViewModel by viewModels()

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
        binding.button.setOnClickListener {

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

    private val clickInputItem = fun (){
        findNavController().navigate(R.id.action_calculatorFragment_to_bottomInputFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}