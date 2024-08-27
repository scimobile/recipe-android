package com.sci.recipeandroid.feature.personalize.ui.fragment

import PersonalizeGoalsUiEvent
import PersonalizeGoalsUiState
import PersonalizeGoalsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentPersonalizeGoalsBinding
import com.sci.recipeandroid.feature.personalize.ui.adapter.PersonalizeGoalsAdapter
import com.sci.recipeandroid.util.updateStatusBarColors
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalizeGoalsFragment : Fragment() {
    private var binding: FragmentPersonalizeGoalsBinding? = null
    private val personalizeGoalsViewModel: PersonalizeGoalsViewModel by viewModel()
    private lateinit var personalizeGoalsAdapter: PersonalizeGoalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalizeGoalsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateStatusBarColors()

        binding?.btnBack?.setOnClickListener {
            findNavController().navigate(R.id.action_personalizeGoalsFragment_to_personalizeOptionsFragment)
        }

        binding?.btnSkip?.setOnClickListener {
            findNavController().navigate(R.id.action_personalizeGoalsFragment_to_nextFragment)
        }

        personalizeGoalsAdapter = PersonalizeGoalsAdapter(onClickItem = {
            personalizeGoalsViewModel.selectItem(it)
        })

        personalizeGoalsViewModel.uiState.observe(viewLifecycleOwner) {
            when (it) {
                PersonalizeGoalsUiState.Loading -> {
                    binding?.loading?.visibility = View.VISIBLE
                }
                is PersonalizeGoalsUiState.Success -> {
                    binding?.loading?.visibility = View.GONE
                    personalizeGoalsAdapter.updateList(it.personalizeGoalsList)
                    binding?.btnContinue?.isEnabled = it.buttonEnabled
                    binding?.btnContinue?.backgroundTintList = if (it.buttonEnabled) {
                        requireContext().getColorStateList(R.color.color_primary)
                    } else {
                        requireContext().getColorStateList(R.color.color_btn_disable)
                    }
                }
                is PersonalizeGoalsUiState.UpdatePersonalizeGoalsList -> {
                    binding?.loading?.visibility = View.GONE
                    personalizeGoalsAdapter.updateList(it.items)
                    binding?.btnContinue?.isEnabled = it.buttonEnabled
                    binding?.btnContinue?.backgroundTintList = if (it.buttonEnabled) {
                        requireContext().getColorStateList(R.color.color_primary)
                    } else {
                        requireContext().getColorStateList(R.color.color_btn_disable)
                    }
                }
                else -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), "Failed to fetch Personalize Goals", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        personalizeGoalsViewModel.uiEvent.observe(viewLifecycleOwner) {
            when(it) {
                is PersonalizeGoalsUiEvent.Error -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(
                        requireContext(),
                        "Error: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {}
            }
        }

        binding?.let {
            it.btnContinue.setOnClickListener {
                findNavController().navigate(R.id.action_personalizeGoalsFragment_to_nextFragment)
            }
            it.rvPersonalizeGoals.adapter = personalizeGoalsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

