package com.sci.recipeandroid.feature.onboarding.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentOnboardBinding
import com.sci.recipeandroid.feature.onboarding.data.OnBoardListProvider
import com.sci.recipeandroid.feature.onboarding.ui.model.OnBoardUiModel
import com.sci.recipeandroid.util.updateStatusBarColors

class OnBoardFragment: Fragment() {
    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var onBoardList: List<OnBoardUiModel>
    private val dots = arrayOfNulls<ImageView>(3)
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateStatusBarColors()

        // Get the onboard list from OnBoardListProvider
        onBoardList = OnBoardListProvider().getOnBoardList()
        onBindSliderCount()
        setUpClickEvent()
    }

    private fun setUpClickEvent() {
        binding.apply {
            btnGetStarted.setOnClickListener {
                findNavController().navigate(R.id.action_onBoardFragment_to_personalizeOptionsFragment)
            }
            btnNext.setOnClickListener {
                if (currentPage < onBoardList.size - 1) {
                    currentPage++
                    onBind()
                }
            }
        }
    }

    private fun onBindSliderCount() {
        val dotsCount = onBoardList.size
        binding.sliderDots.removeAllViews()

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
            binding.sliderDots.addView(dots[i], params)
        }

        dots[currentPage]?.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.indicator_active))
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

        val data = onBoardList[currentPage]
        binding.apply {
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
                ivlogo.visibility = View.VISIBLE
                btnGetStarted.visibility = View.VISIBLE
            } else {
                btnNext.visibility = View.VISIBLE
                ivlogo.visibility = View.INVISIBLE
                btnGetStarted.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}