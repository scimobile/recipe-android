package com.sci.recipeandroid.feature.detail.ui.screen

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.sci.recipeandroid.databinding.FragmentDetailBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailHeaderContainer
import com.sci.recipeandroid.feature.detail.ui.adapter.DetailHeaderAdapter
import com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter.DetailCenterAdapter
import com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter.DetailFooterContainerAdapter
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding
        get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolBar = binding.toolBar
        val detailRecyclerView = binding.detailRecyclerView
        val navBackImg = binding.navigateBackImg
        adjustStatusBar(toolBar)

        val detailHeaderAdapter = DetailHeaderAdapter {}
        val detailCenterAdapter = DetailCenterAdapter {}
        val detailFooterContainerAdapter = DetailFooterContainerAdapter {}

        val detailAdapter = ConcatAdapter(
            detailHeaderAdapter, detailCenterAdapter,
            detailFooterContainerAdapter
        )

        detailViewModel.detailData.observe(viewLifecycleOwner) { data ->
            data.detailScreenData.onEach {
                when (it) {
                    is DetailHeaderContainer -> {
                        detailHeaderAdapter.updateList(listOf(it))
                    }

                    is DetailCenterContainer -> {
                        detailCenterAdapter.updateList(listOf(it))
                    }

                    is DetailFooterContainer -> {
                        detailFooterContainerAdapter.updateList(listOf(it))
                    }
                }
            }

        }


        detailRecyclerView.adapter = detailAdapter
        detailRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(), LinearLayoutManager.VERTICAL, false
        )
        detailRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val scrollY = recyclerView.computeVerticalScrollOffset()
                adjustToolbarVisibility(
                    scrollY,
                    toolBar,
                    navBackImg,
                )
            }
        })


    }

    private fun adjustStatusBar(
        toolBar: MaterialToolbar
    ) {
        ViewCompat.setOnApplyWindowInsetsListener(toolBar) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
        val window = requireActivity().window
        window.statusBarColor = Color.WHITE
    }

    private fun adjustToolbarVisibility(
        scrollY: Int,
        toolBar: MaterialToolbar,
        navBackImg: ImageView,
    ) {
        val maxScroll = 950 // adjust this value based on your need

        // Calculate the fraction of the scroll (from 0 to 1)
        val fraction = (scrollY.coerceIn(0, maxScroll)).toFloat() / maxScroll
        toolBar.background.alpha = (255 * (fraction)).toInt()
        // Define start and end colors
        val imgStartColor = navBackImg.imageTintList?.defaultColor
        val imgEndColor = Color.BLACK
        // Calculate the alpha value (between 0 and 1)
        navBackImg.background.alpha = (255 * (1 - fraction)).toInt()
        navBackImg.setColorFilter(blendColors(imgStartColor!!, imgEndColor, fraction))
    }

    private fun blendColors(startColor: Int, endColor: Int, fraction: Float): Int {
        val startAlpha = Color.alpha(startColor)
        val startRed = Color.red(startColor)
        val startGreen = Color.green(startColor)
        val startBlue = Color.blue(startColor)

        val endAlpha = Color.alpha(endColor)
        val endRed = Color.red(endColor)
        val endGreen = Color.green(endColor)
        val endBlue = Color.blue(endColor)

        val blendedAlpha = (startAlpha + ((endAlpha - startAlpha) * fraction)).toInt()
        val blendedRed = (startRed + ((endRed - startRed) * fraction)).toInt()
        val blendedGreen = (startGreen + ((endGreen - startGreen) * fraction)).toInt()
        val blendedBlue = (startBlue + ((endBlue - startBlue) * fraction)).toInt()

        return Color.argb(blendedAlpha, blendedRed, blendedGreen, blendedBlue)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}