package com.sci.recipeandroid.feature.onboarding.ui.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fadeInAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up)
        binding.tvWelcome.startAnimation(fadeInAnimation)
        binding.tvWelcome.visibility = View.VISIBLE
        binding.ivLogo.startAnimation(fadeInAnimation)
        binding.ivLogo.visibility = View.VISIBLE
        binding.root.setOnClickListener {
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToOnBoardFragment())
        }

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_onBoardFragment)
        }, 3000)
    }

}