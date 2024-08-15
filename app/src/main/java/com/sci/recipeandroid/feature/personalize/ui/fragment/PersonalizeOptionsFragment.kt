package com.sci.recipeandroid.feature.personalize.ui.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentPersonalizeOptionsBinding
import com.sci.recipeandroid.feature.personalize.customtagcontainer.TagView
import com.sci.recipeandroid.feature.personalize.ui.adapter.DietRecipeAdapter
import com.sci.recipeandroid.feature.personalize.ui.viewmodel.PersonalizeUiEvent
import com.sci.recipeandroid.feature.personalize.ui.viewmodel.PersonalizeUiState
import com.sci.recipeandroid.feature.personalize.ui.viewmodel.PersonalizeViewModel
import okhttp3.internal.notify
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalizeOptionsFragment : Fragment() {
    private var binding: FragmentPersonalizeOptionsBinding? = null
    private lateinit var mAdatper: DietRecipeAdapter
    private val personalizeViewModel: PersonalizeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalizeOptionsBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdatper = DietRecipeAdapter(onClickItem = { isSelected ->
            personalizeViewModel.onDietRecipeItemSelected(isSelected)
        })
        binding?.rvRecipeDiet?.apply {
            adapter = mAdatper
            layoutManager = GridLayoutManager(requireContext(),4)
        }

        personalizeViewModel.uiState.observe(viewLifecycleOwner) {
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
            }
        }

        personalizeViewModel.uiEvent.observe(viewLifecycleOwner) {
            Log.d("observerState", it.toString())
            when (it) {
                is PersonalizeUiEvent.Error -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        }

        personalizeViewModel.isButtonEnabled.observe(viewLifecycleOwner) { isEnabled ->
            if (isEnabled) {
                binding?.btnContinue?.isEnabled = true
                binding?.btnContinue?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_primary))
            } else {
                binding?.btnContinue?.isEnabled = false
                binding?.btnContinue?.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_btn_disable))
            }
        }

        binding?.mTagAllergiesIngredient?.setOnTagClickListener( object: TagView.OnTagClickListener {
            override fun onTagClick(position: Int, text: String?) {
                val tagView = binding?.mTagAllergiesIngredient?.getTagView(position)
                tagView?.let {
                    it.isSelected = !it.isSelected
                    (if (it.isSelected) {
                        personalizeViewModel.onAllergiesIngredientItemSelected(it.isSelected)
                        it.tagBackgroundColor = ContextCompat.getColor(requireContext(), R.color.color_primary)
                        it.setTagTextColor(Color.WHITE)
                    } else {
                        personalizeViewModel.onAllergiesIngredientItemSelected(it.isSelected)
                        it.tagBackgroundColor = ContextCompat.getColor(requireContext(), R.color.color_surface)
                        it.setTagTextColor(ContextCompat.getColor(requireContext(), R.color.color_text_secondary))
                    })
                }
            }

            override fun onTagLongClick(position: Int, text: String?) {

            }

            override fun onSelectedTagDrag(position: Int, text: String?) {

            }

            override fun onTagCrossClick(position: Int) {

            }

        })

        binding?.btnContinue?.setOnClickListener {
            goToPersonalizeGoals()
        }

        binding?.btnSkip?.setOnClickListener {
            goToPersonalizeGoals()
        }

    }

    private fun goToPersonalizeGoals() {
        findNavController().navigate(R.id.action_personalizeOptionsFragment_to_personalizeGoalsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}