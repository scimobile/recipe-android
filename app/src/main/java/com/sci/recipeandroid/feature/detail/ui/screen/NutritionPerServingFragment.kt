package com.sci.recipeandroid.feature.detail.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sci.recipeandroid.databinding.FragmentNutritionBinding
import com.sci.recipeandroid.feature.detail.ui.adapter.NutritionAdapter
import com.sci.recipeandroid.feature.detail.ui.viewmodel.NutritionViewModel
import com.sci.recipeandroid.util.SystemUiController.adjustStatusBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionPerServingFragment : Fragment() {
    private var _binding: FragmentNutritionBinding? = null
    val binding
        get() = _binding!!

    private var detailId: Double? = null

    private val viewModel: NutritionViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNutritionBinding.inflate(inflater, container, false)
        detailId = this.arguments?.getDouble("id")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolBar = binding.nutritionToolBar
        adjustStatusBar(toolBar, requireActivity())
        detailId?.let { viewModel.getNutritionData(it) }
        val recyclerView = binding.nutritionPerServeRv
        val adapter = NutritionAdapter()
        recyclerView.adapter = adapter
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        viewModel.nutritionScnState.observe(viewLifecycleOwner) {
            when(it){
                is NutritionViewModel.NutritionScreenState.Error -> {

                }
                is NutritionViewModel.NutritionScreenState.Loading -> {

                }
                is NutritionViewModel.NutritionScreenState.Success -> {
                    adapter.updateList(it.data)
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}