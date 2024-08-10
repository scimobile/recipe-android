package com.sci.recipeandroid.feature.onboarding.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentOnboardBinding
import com.sci.recipeandroid.feature.onboarding.ui.model.OnBoardModel

class OnBoardFragment: Fragment() {
    private var binding: FragmentOnboardBinding? = null
    private var onBoardList = mutableListOf<OnBoardModel>()
    val dots = arrayOfNulls<ImageView>(3)
    var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onBoardList.addAll(
            listOf(
                OnBoardModel(
                    title = "Personalized Recipe Discovery",
                    description = "Tell us your food preferences and we’ll only serve you delicious recipes ideas.",
                    imagePath = R.drawable.onboard1
                ),
                OnBoardModel(
                    title = "Never Forget an Ingredient",
                    description = "Build your weekly grocery list and stay organized while you shop..",
                    imagePath = R.drawable.onboard2
                ),
                OnBoardModel(
                    title = "Your Favourite Recipes at Your Fingertips",
                    description = "Save time on planning by having your favourite recipes always within reach.",
                    imagePath = R.drawable.onboard3
                )
            )
        )
        onBindSliderCount()
    }

    private fun clickEvent() {
        binding?.apply {
            btnGetStarted.setOnClickListener {

            }
            btnNext.setOnClickListener {
                currentPage++
                onBind()
            }
        }
    }

    private fun onBindSliderCount() {
        val dotsCount = onBoardList.size
        binding?.apply {
            sliderDots.removeAllViews()
        }

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(requireContext())
            dots[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.indicator_inactive
                )
            )
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(8, 0, 8, 0)
            binding?.apply {
                sliderDots.addView(dots[i], params)
            }
        }
        dots[currentPage]?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.indicator_inactive))
        onBind()

    }

    private fun onBind() {
        for (i in 0 until onBoardList.size) {
            dots[i]?.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.indicator_inactive
                )
            )
        }

        dots[currentPage]?.setImageDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                R.drawable.indicator_active
            )
        )
        binding?.apply {
            val data = onBoardList[currentPage]
            data.imagePath?.let {
                Glide.with(requireContext())
                    .load(it)
                    .into(ivOnBoardImage)
            }
            tvTitle.text = data.title
            tvSubtitle.text = data.description

            val totalPage = onBoardList.size - 1

            if (currentPage == totalPage) {
                btnNext.visibility = View.INVISIBLE
                btnGetStarted.visibility = View.VISIBLE
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}