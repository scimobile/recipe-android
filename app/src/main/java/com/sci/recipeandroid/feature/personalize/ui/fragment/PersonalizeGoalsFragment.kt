package com.sci.recipeandroid.feature.personalize.ui.fragment

import PersonalizeGoalsUiEvent
import PersonalizeGoalsViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentPersonalizeGoalsBinding
import com.sci.recipeandroid.feature.personalize.ui.adapter.PersonalizeGoalsAdapter
import com.sci.recipeandroid.util.exhaustive
import org.koin.androidx.viewmodel.ext.android.viewModel

class PersonalizeGoalsFragment : Fragment() {
    private var binding: FragmentPersonalizeGoalsBinding? = null
    private val personalizeGoalsViewModel: PersonalizeGoalsViewModel by viewModel()
    private lateinit var personlizeGoalsAdapter: PersonalizeGoalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalizeGoalsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.btnBack?.setOnClickListener {
            findNavController().navigate(R.id.action_personalizeGoalsFragment_to_personalizeOptionsFragment)
        }

        binding?.btnSkip?.setOnClickListener {
            findNavController().navigate(R.id.action_personalizeGoalsFragment_to_nextFragment)
        }

        binding?.btnContinue?.setOnClickListener {
            findNavController().navigate(R.id.action_personalizeGoalsFragment_to_nextFragment)
        }

        personlizeGoalsAdapter = PersonalizeGoalsAdapter(onClickItem = { selectedGoal ->
            personalizeGoalsViewModel.selectGoal(selectedGoal)
        })

        personalizeGoalsViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is PersonalizeGoalsUiState.Loading -> {
                    binding?.loading?.visibility = View.VISIBLE
                }
                is PersonalizeGoalsUiState.Success -> {
                    binding?.loading?.visibility = View.GONE
                    personlizeGoalsAdapter.submitList(uiState.personalizeGoalsList)
                }
                else -> {
                    binding?.loading?.visibility = View.GONE
                    Toast.makeText(requireContext(), "Unknown state encountered", Toast.LENGTH_SHORT)
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

        // Observe Selected Goal
        personalizeGoalsViewModel.selectedGoal.observe(viewLifecycleOwner) { selectedGoal ->
            if (selectedGoal != null) {
                binding?.btnContinue?.isEnabled = true
                binding?.btnContinue?.backgroundTintList =
                    requireContext().getColorStateList(R.color.color_primary)
            } else {
                binding?.btnContinue?.isEnabled = false
                binding?.btnContinue?.backgroundTintList =
                    requireContext().getColorStateList(R.color.color_btn_disable)
            }
        }

        // Set up RecyclerView
        binding?.rvPersonalizeGoals?.apply {
            adapter = personlizeGoalsAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        // Continue Button Click Listener

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
