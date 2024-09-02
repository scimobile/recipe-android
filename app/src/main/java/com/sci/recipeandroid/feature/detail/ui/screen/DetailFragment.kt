package com.sci.recipeandroid.feature.detail.ui.screen

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentDetailBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailHeaderContainer
import com.sci.recipeandroid.feature.detail.ui.adapter.DetailHeaderAdapter
import com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter.DetailCenterAdapter
import com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter.DetailFooterContainerAdapter
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DetailViewModel
import com.sci.recipeandroid.util.ColorUtil.blendColors
import com.sci.recipeandroid.util.SystemUiController.adjustStatusBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding
        get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModel()
    private val detailId  = 1.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        detailViewModel.getDetailData(detailId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolBar = binding.toolBar
        val detailRecyclerView = binding.detailRecyclerView
        val navBackBtn = binding.navigateBackImg
        val savedBtn = binding.saveBtn
        val sharedBtn = binding.shareBtn
        adjustStatusBar(toolBar, requireActivity())

        savedBtn.setOnClickListener {
            detailViewModel.onEvent(
                DetailViewModel.ScreenEvent.UpdateSavedItem(1.0)
            )
        }

        val detailHeaderAdapter = DetailHeaderAdapter {}
        val detailCenterAdapter = DetailCenterAdapter(
            goToViewDirection = {

            },
            goToAllNutrition = {

            },
            giveAmountResult = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.UpdateServeAmt(it)
                )
            }
        )
        val detailFooterContainerAdapter = DetailFooterContainerAdapter(
            onCompleteMealSaved = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.UpdateSavedItem(it)
                )
            },
            onAlsoLikeSaved = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.UpdateSavedItem(it)
                )
            }
        )

        val detailAdapter = ConcatAdapter(
            detailHeaderAdapter, detailCenterAdapter,
            detailFooterContainerAdapter
        )

        detailViewModel.detailData.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is DetailViewModel.DetailScreenState.Loading -> {
                    /*ToDo Write the loading logic */
                }

                is DetailViewModel.DetailScreenState.Success -> {
                    screenState.data.detailScreenData.onEach { data ->
                        when (data) {
                            is DetailHeaderContainer -> {
                                detailHeaderAdapter.updateList(listOf(data))
                            }

                            is DetailCenterContainer -> {
                                detailCenterAdapter.updateList(listOf(data))
                            }

                            is DetailFooterContainer -> {
                                detailFooterContainerAdapter.updateList(listOf(data))
                            }
                        }
                    }
                }

                is DetailViewModel.DetailScreenState.Error -> {
                    /*ToDo Write the error logic */
                }

            }
        }

        detailViewModel.detailUpdateState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is DetailViewModel.DetailScreenUpdateState.IngredientUpdate -> {
                    detailCenterAdapter.updateIngredientList(screenState.ingredientList)
                }

                is DetailViewModel.DetailScreenUpdateState.SavedItemUpdate -> {
                    Log.d("ISBookMarkMY", "${screenState.detailSavedUiModel.detailIdList}")
                    if (screenState.detailSavedUiModel.detailIdList) {
                        savedBtn.setIconResource(R.drawable.save_fill_ic)
                        savedBtn.setIconTintResource(
                            if (detailRecyclerView.computeVerticalScrollOffset() > 850) {
                                R.color.black
                            } else {
                                R.color.white
                            }
                        )
                    } else {
                        savedBtn.setIconResource(R.drawable.save_outline_ic)
                        savedBtn.setIconTintResource(
                            if (detailRecyclerView.computeVerticalScrollOffset() > 850) {
                                R.color.black
                            } else {
                                R.color.white
                            }
                        )
                    }
                    detailFooterContainerAdapter.updateList(
                        listOf(screenState.detailSavedUiModel.detailFooterContainer)
                    )
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
                    navBackBtn,
                    savedBtn, sharedBtn
                )
            }
        })


    }


    private fun adjustToolbarVisibility(
        scrollY: Int,
        toolBar: MaterialToolbar,
        backBtn: MaterialButton,
        savedBtn: MaterialButton,
        sharedBtn: MaterialButton
    ) {
        val maxScroll = 950

        // Calculate the fraction of the scroll (from 0 to 1)
        val fraction = (scrollY.coerceIn(0, maxScroll)).toFloat() / maxScroll
        toolBar.background.alpha = (255 * (fraction)).toInt()
        // Define start and end colors
        val iconStartColor = backBtn.iconTint?.defaultColor ?: Color.LTGRAY
        val iconEndColor = Color.BLACK
        val bgStartColor = backBtn.backgroundTintList?.defaultColor ?: Color.WHITE
        val bgEndColor = Color.WHITE

        // Blend the colors based on the fraction
        val iconBlendedColor = blendColors(iconStartColor, iconEndColor, fraction)
        val rippleBlendedColor = blendColors(Color.LTGRAY, Color.GRAY, fraction)

        backBtn.background.setTintList(
            ColorStateList.valueOf(
                blendColors(bgStartColor, bgEndColor, fraction)
            )
        )
        backBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        backBtn.icon.setTintList(ColorStateList.valueOf(iconBlendedColor))

        savedBtn.background.setTintList(
            ColorStateList.valueOf(
                blendColors(bgStartColor, bgEndColor, fraction)
            )
        )
        savedBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        savedBtn.icon.setTintList(ColorStateList.valueOf(iconBlendedColor))

        sharedBtn.background.setTintList(
            ColorStateList.valueOf(
                blendColors(bgStartColor, bgEndColor, fraction)
            )
        )
        sharedBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        sharedBtn.icon.setTintList(ColorStateList.valueOf(iconBlendedColor))
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}