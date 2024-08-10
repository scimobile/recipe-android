package com.sci.recipeandroid.feature.personalize.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentPersonalizeGoalsBinding

class PersonalizeGoalsFragment : Fragment() {
    private lateinit var binding: FragmentPersonalizeGoalsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalizeGoalsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBack.setOnClickListener {
            findNavController().navigate(R.id.action_personalizeGoalsFragment_to_personalizeOptionsFragment)
        }
    }
}