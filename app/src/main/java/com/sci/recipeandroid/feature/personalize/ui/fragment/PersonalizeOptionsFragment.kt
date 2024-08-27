package com.sci.recipeandroid.feature.personalize.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentPersonalizeOptionsBinding
import com.sci.recipeandroid.feature.personalize.ui.adapter.AllergiesIngredientAdapter
import com.sci.recipeandroid.feature.personalize.ui.adapter.DietRecipeAdapter
import com.sci.recipeandroid.feature.personalize.ui.viewmodel.AllergiesIngredientUiState
import com.sci.recipeandroid.feature.personalize.ui.viewmodel.DietRecipeUiState
import com.sci.recipeandroid.feature.personalize.ui.viewmodel.PersonalizeUiEvent
import com.sci.recipeandroid.feature.personalize.ui.viewmodel.PersonalizeViewModel
import com.sci.recipeandroid.util.updateStatusBarColors
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalizeOptionsFragment : Fragment() {
    private var binding: FragmentPersonalizeOptionsBinding? = null
    private val personalizeViewModel: PersonalizeViewModel by viewModel()
    private lateinit var dietRecipeAdapter: DietRecipeAdapter
    private lateinit var allergiesIngredientAdapter: AllergiesIngredientAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalizeOptionsBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateStatusBarColors()

        dietRecipeAdapter = DietRecipeAdapter(onClickItem = {
            personalizeViewModel.selectItemDietRecipe(it)
        })

        personalizeViewModel.uiStateDietRecipe.observe(viewLifecycleOwner) {
            when (it) {
                DietRecipeUiState.Loading -> {
                    binding?.loading?.visibility = View.VISIBLE
                }
                is DietRecipeUiState.Success -> {
                    binding?.loading?.visibility = View.GONE
                    dietRecipeAdapter.updateList(it.dietRecipeList)
                }
                is DietRecipeUiState.UpdateDietRecipeList -> {
                    binding?.loading?.visibility = View.GONE
                    dietRecipeAdapter.updateList(it.items)
                }
                else -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), "Failed to fetch Diet Recipes", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding?.rvDietRecipe?.apply {
            adapter = dietRecipeAdapter
        }

        allergiesIngredientAdapter = AllergiesIngredientAdapter(onClickItem = {
            personalizeViewModel.selectItemAllergiesIngredient(it)
        })

        personalizeViewModel.uiStateAllergiesIngredient.observe(viewLifecycleOwner) {
            when (it) {
                AllergiesIngredientUiState.Loading -> {
                    binding?.loading?.visibility = View.VISIBLE
                }
                is AllergiesIngredientUiState.Success -> {
                    binding?.loading?.visibility = View.GONE
                    allergiesIngredientAdapter.updateList(it.allergiesIngredientList)
                }
                is AllergiesIngredientUiState.UpdateAllergiesIngredientList -> {
                    binding?.loading?.visibility = View.GONE
                    allergiesIngredientAdapter.updateList(it.items)
                }
                else -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), "Failed to fetch Allergies Ingredients", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        binding?.rvAllergiesIngredient?.apply {
            adapter = allergiesIngredientAdapter
            layoutManager = FlexboxLayoutManager(context).apply {
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }
        }

        binding?.btnSkip?.setOnClickListener {
            findNavController().navigate(R.id.action_personalizeOptionsFragment_to_personalizeGoalsFragment)
        }

        val observer = { _: Any? ->
            updateContinueButtonState()
        }

        personalizeViewModel.uiStateDietRecipe.observe(viewLifecycleOwner, observer)
        personalizeViewModel.uiStateAllergiesIngredient.observe(viewLifecycleOwner, observer)


        personalizeViewModel.uiEvent.observe(viewLifecycleOwner) {
            Log.d("observerState", it.toString())
            when (it) {
                is PersonalizeUiEvent.Error -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun updateContinueButtonState() {
        val isDietRecipeSelected = personalizeViewModel.uiStateDietRecipe.value != null
        val isAllergiesIngredientSelected = personalizeViewModel.allergiesIngredientUiModel.any { it.isSelect }

        binding?.btnContinue?.isEnabled = isDietRecipeSelected && isAllergiesIngredientSelected

        if (binding?.btnContinue?.isEnabled == true) {
            binding?.btnContinue?.backgroundTintList = requireContext().getColorStateList(R.color.color_primary)
            binding?.btnContinue?.setOnClickListener {
                findNavController().navigate(R.id.action_personalizeOptionsFragment_to_personalizeGoalsFragment)
            }
        } else {
            binding?.btnContinue?.backgroundTintList = requireContext().getColorStateList(R.color.color_btn_disable)
            binding?.btnContinue?.setOnClickListener(null)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}