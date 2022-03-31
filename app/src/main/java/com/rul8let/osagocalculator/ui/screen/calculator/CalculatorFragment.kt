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
import com.rul8let.osagocalculator.ui.binding.bindCoefficientCard
import com.rul8let.osagocalculator.ui.model.CompanySealed.CompanyItem
import com.rul8let.osagocalculator.ui.util.safeNavigate
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
        binding.bindAdapterInput()
        binding.coefficientsInfo.bindCoefficientCard(
            viewModel.expandedCoefficientCard,
            viewModel.coefficientList,
            viewLifecycleOwner ) { expand ->
            viewModel.changeExpanded(expand)
        }

        viewModel.enabledButton.observe(viewLifecycleOwner){
            binding.button.isEnabled = it
        }

        binding.button.setOnClickListener {
            findNavController().safeNavigate(CalculatorFragmentDirections.actionCalculatorFragmentToPriceCalculationFragment())
        }

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<CompanyItem>(keySelectItemPrice)
            ?.observe(viewLifecycleOwner) { priceData->
                val action = CalculatorFragmentDirections
                    .actionCalculatorFragmentToBottomSelectCompanyFragment(priceData)
                findNavController().safeNavigate(action)
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
        findNavController().safeNavigate(CalculatorFragmentDirections.actionCalculatorFragmentToBottomInputFragment())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val keySelectItemPrice = "keySelectItemPrice"
    }
}