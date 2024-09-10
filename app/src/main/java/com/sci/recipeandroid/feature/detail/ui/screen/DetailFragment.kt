package com.sci.recipeandroid.feature.detail.ui.screen

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.content.res.Configuration
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
import com.sci.recipeandroid.feature.detail.domain.model.DetailHeaderContainerModel
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
    private val detailId = 1.0

    //saved the screen state in bundle for screen rotation and navigation
    private var savedScreenState: Bundle? = null

    //saved the color of the tool bar icon in bundle for screen rotation
    private var savedColor: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        adjustStatusBar(binding.toolBar, requireActivity())
        if (savedInstanceState == null && savedScreenState == null) {
            detailViewModel.getDetailData(detailId)
        }
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolBar = binding.toolBar
        val detailRecyclerView = binding.detailRecyclerView
        val navBackBtn = binding.navigateBackImg
        val savedBtn = binding.saveBtn
        val sharedBtn = binding.shareBtn

        savedBtn.setOnClickListener {
            detailViewModel.onEvent(
                DetailViewModel.ScreenEvent.UpdateSavedItem(1.0)
            )
        }

        val detailHeaderAdapter = DetailHeaderAdapter {}
        val detailCenterAdapter = DetailCenterAdapter(
            goToViewDirection = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.NavigateToDirection(detailId)
                )
            },
            goToAllNutrition = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.NavigateToNutrition(detailId)
                )
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

        detailViewModel.detailScnState.observe(viewLifecycleOwner) { screenState ->
            when (screenState) {
                is DetailViewModel.DetailScreenState.Loading -> {
                    /*ToDo Write the loading logic */
                }

                is DetailViewModel.DetailScreenState.Success -> {
                    screenState.data.detailScreenData.onEach { data ->
                        when (data) {
                            is DetailHeaderContainerModel -> {
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
                is DetailViewModel.DetailScreenUpdateState.SavedItemUpdate -> {
                    adjustBookMarkIcon(
                        isBookMark = screenState.detailSavedUiModel.detailIdList,
                        savedBtn = savedBtn,
                        detailRecyclerView = detailRecyclerView
                    )
                    detailFooterContainerAdapter.updateList(
                        listOf(screenState.detailSavedUiModel.detailFooterContainer)
                    )
                }
                is DetailViewModel.DetailScreenUpdateState.IngredientUpdate -> {
                    detailCenterAdapter.updateIngredientList(screenState.ingredientList)
                }
            }
        }

        detailViewModel.detailNavigation.observe(viewLifecycleOwner) {
            when (it) {
                is DetailViewModel.DetailNavigation.NavigateToDirection -> {
                    val directionFragment = DirectionFragment()
                    val bundle = Bundle()
                    bundle.putDouble("id", it.id)
                    directionFragment.arguments = bundle

                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.host_fragment, directionFragment)
                        .addToBackStack(DetailFragment::class.simpleName)
                        .commit()
                }

                is DetailViewModel.DetailNavigation.NavigateToNutrition -> {
                    val nutritionFragment = NutritionFragment()
                    val bundle = Bundle()
                    bundle.putDouble("id", it.id)
                    nutritionFragment.arguments = bundle

                    requireActivity().supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.host_fragment, nutritionFragment)
                        .addToBackStack(DetailFragment::class.simpleName)
                        .commit()
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
                    savedBtn, sharedBtn,
                    savedInstanceState
                )
            }
        })
    }


    private fun adjustToolbarVisibility(
        scrollY: Int,
        toolBar: MaterialToolbar,
        backBtn: MaterialButton,
        savedBtn: MaterialButton,
        sharedBtn: MaterialButton,
        savedInstanceState: Bundle?
    ) {
        val maxScroll = 950

        // Calculate the fraction of the scroll (from 0 to 1)
        val fraction = (scrollY.coerceIn(0, maxScroll)).toFloat() / maxScroll
        toolBar.background.alpha = (255 * (fraction)).toInt()
        if (scrollY > maxScroll) {
            toolBar.elevation = 20f
        } else {
            toolBar.elevation = 0f
        }
        // Define start and end colors
        val iconStartColor = backBtn.iconTint?.defaultColor ?: Color.LTGRAY
        val iconEndColor = Color.BLACK
        val bgStartColor = backBtn.backgroundTintList?.defaultColor ?: Color.WHITE
        val bgEndColor = Color.WHITE

        // Blend the colors based on the fraction
        val getSavedColor = savedInstanceState?.getBundle("color")?.getIntArray("BlendedColor")
        val iconBlendedColor =
            getSavedColor?.get(0) ?: blendColors(iconStartColor, iconEndColor, fraction)
        val rippleBlendedColor =
            getSavedColor?.get(1) ?: blendColors(Color.LTGRAY, Color.GRAY, fraction)
        val bgBlendedColor =
            getSavedColor?.get(2) ?: blendColors(bgStartColor, bgEndColor, fraction)

        savedColor = Bundle().apply {
            this.putIntArray(
                "BlendedColor", intArrayOf(
                    iconBlendedColor, rippleBlendedColor, bgBlendedColor
                )
            )
        }


        backBtn.background.setTintList(ColorStateList.valueOf(bgBlendedColor))
        backBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        backBtn.icon.setTintList(ColorStateList.valueOf(iconBlendedColor))

        savedBtn.background.setTintList(ColorStateList.valueOf(bgBlendedColor))
        savedBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        savedBtn.icon.setTintList(ColorStateList.valueOf(iconBlendedColor))

        sharedBtn.background.setTintList(ColorStateList.valueOf(bgBlendedColor))
        sharedBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        sharedBtn.icon.setTintList(ColorStateList.valueOf(iconBlendedColor))

    }

    private fun adjustBookMarkIcon(
        isBookMark: Boolean, savedBtn: MaterialButton,
        detailRecyclerView: RecyclerView
    ) {
        if (isBookMark) {
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
    }

    private fun saveFragState() = Bundle().apply { this.putString("ScreenState", "Save") }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle("detail", saveFragState())
        outState.putBundle("color", savedColor)
    }

    override fun onStop() {
        super.onStop()
        savedScreenState = saveFragState()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}