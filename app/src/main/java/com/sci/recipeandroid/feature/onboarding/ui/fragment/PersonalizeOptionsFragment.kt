package com.sci.recipeandroid.feature.onboarding.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentPersonalizeOptionsBinding
import com.sci.recipeandroid.feature.onboarding.ui.adapter.DietRecipeAdapter
import com.sci.recipeandroid.feature.onboarding.ui.viewmodel.PersonalizeUiState
import com.sci.recipeandroid.feature.onboarding.ui.viewmodel.PersonalizeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalizeOptionsFragment : Fragment() {
    private var binding: FragmentPersonalizeOptionsBinding? = null
    private lateinit var mAdatper: DietRecipeAdapter
    private val bookViewModel: PersonalizeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalizeOptionsBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdatper = DietRecipeAdapter(onClickItem = {})
        binding?.rvRecipeDiet?.apply {
            adapter = mAdatper
            layoutManager = GridLayoutManager(requireContext(),4)
        }

        bookViewModel.liveData.observe(viewLifecycleOwner) {
            Log.d("observerState", it.toString())
            when (it) {
                PersonalizeUiState.Loading -> {
                    binding?.loading?.visibility = View.VISIBLE
                }

                is PersonalizeUiState.Success -> {
                    binding?.loading?.visibility = View.GONE
                    mAdatper.updateList(it.personalizeData.dietRecipe)
                    it.personalizeData.allergiesIngredient.map {
                        it.name?.let { it1 -> binding?.mTagAllergiesIngredient?.addTag(it1)}
                    }
                }

                is PersonalizeUiState.Error -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}