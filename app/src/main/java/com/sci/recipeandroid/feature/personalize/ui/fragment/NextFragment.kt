package com.sci.recipeandroid.feature.personalize.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sci.recipeandroid.databinding.FragmentNextBinding
import com.sci.recipeandroid.databinding.FragmentPersonalizeGoalsBinding

class NextFragment : Fragment() {

    private lateinit var binding: FragmentNextBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNextBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}