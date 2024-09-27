package com.sci.recipeandroid.feature.detail.ui.screen

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
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
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailHeaderContainerModel
import com.sci.recipeandroid.feature.detail.ui.adapter.DetailHeaderAdapter
import com.sci.recipeandroid.feature.detail.ui.adapter.detailcenter.DetailCenterAdapter
import com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter.DetailFooterContainerAdapter
import com.sci.recipeandroid.feature.detail.ui.models.DetailCenterUiModels
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DetailViewModel
import com.sci.recipeandroid.util.ColorUtil.blendColors
import com.sci.recipeandroid.util.ColorUtil.blendRGBColors
import com.sci.recipeandroid.util.SharedIntentBuilder
import com.sci.recipeandroid.util.SystemUiController.adjustNavigationBar
import com.sci.recipeandroid.util.SystemUiController.adjustStatusBar
import com.sci.recipeandroid.util.setOneTimeClickListener
import io.ktor.http.CacheControl
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    val binding
        get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModel()
    private val detailId = 1.0

    //saved the screen state in bundle for screen rotation and navigation
    private var savedScreenState: Bundle? = null

    //saved the add to cart state in bundle for screen rotation
    private var savedBookMarkState: Bundle? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        adjustStatusBar(binding.toolBar, requireActivity(), Color.WHITE)
        adjustNavigationBar(binding.buttonGradientFramelayout, requireActivity(), Color.TRANSPARENT)
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
        val addToCartBtn = binding.addToCartBtn

        savedBtn.setOnClickListener {
            detailViewModel.onEvent(
                DetailViewModel.ScreenEvent.UpdateSavedItem(1.0)
            )
        }

        sharedBtn.setOneTimeClickListener {
            startActivity(SharedIntentBuilder("message").textSenderIntent())
        }

        addToCartBtn.setOneTimeClickListener {
            detailViewModel.onEvent(
                DetailViewModel.ScreenEvent.AddToCart(detailId)
            )
        }

        //region adapter set up
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
            },
            addToCart = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.AddToCart(detailId)
                )
            },
            onSelectUnit = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.UpdateUnit(it)
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
            },
            onCompleteMealAddToCartClick = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.AddToCart(it)
                )
            },
            onAlsoLikeAddToCartClick = {
                detailViewModel.onEvent(
                    DetailViewModel.ScreenEvent.AddToCart(it)
                )
            }
        )
        //endregion

        val detailAdapter = ConcatAdapter(
            detailHeaderAdapter,
            detailCenterAdapter,
            detailFooterContainerAdapter
        )
        //region screen state observer
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

                            is DetailCenterUiModels -> {
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
            Log.wtf("ScreenState", "$screenState")
            when (screenState) {
                is DetailViewModel.DetailScreenUpdateState.SavedItemUpdate -> {

                    adjustBookMarkIcon(
                        isBookMark = screenState.detailSavedUiModel.isBookMarked,
                        savedBtn = savedBtn,
                        detailRecyclerView = detailRecyclerView
                    )
                    detailFooterContainerAdapter.updateList(
                        listOf(screenState.detailSavedUiModel.detailFooterContainer)
                    )
                    detailCenterAdapter.updateList(
                        screenState.detailCenterUiModels
                    )
                    setUpAddToCartState(
                        isAddedToCart = screenState.isAddedToCart,
                        addToCartBtn = addToCartBtn
                    )
                }

                is DetailViewModel.DetailScreenUpdateState.DetailCenterUpdate -> {
                    detailCenterAdapter.updateList(
                        screenState.detailCenterDataList
                    )
                    setUpAddToCartState(
                        isAddedToCart = screenState.isAddedToCart,
                        addToCartBtn = addToCartBtn
                    )
                    adjustBookMarkIcon(
                        isBookMark = screenState.isBookMarked,
                        savedBtn = savedBtn,
                        detailRecyclerView = detailRecyclerView
                    )
                }

                is DetailViewModel.DetailScreenUpdateState.AddToCartUpdate -> {
                    detailFooterContainerAdapter.updateList(
                        listOf(screenState.detailFooterContainer)
                    )
                    detailCenterAdapter.updateList(
                        screenState.detailCenterUiModels
                    )
                    setUpAddToCartState(
                        isAddedToCart = screenState.isAddedToCart,
                        addToCartBtn = addToCartBtn
                    )

                    adjustBookMarkIcon(
                        isBookMark = screenState.isBookMarked,
                        savedBtn = savedBtn,
                        detailRecyclerView = detailRecyclerView
                    )
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

        //endregion
        detailRecyclerView.adapter?.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.ALLOW

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
                    savedBtn,
                    sharedBtn,
                )
            }
        })
    }

    private fun setUpAddToCartState(
        isAddedToCart: Boolean,
        addToCartBtn: MaterialButton
    ) {
        if (isAddedToCart) {
            addToCartBtn.isEnabled = false
            addToCartBtn.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(R.color.color_button_inactive)
            )
            addToCartBtn.setTextColor(Color.WHITE)
        } else {
            addToCartBtn.backgroundTintList = ColorStateList.valueOf(
                requireContext().getColor(R.color.color_button_active)
            )
            addToCartBtn.setTextColor(Color.WHITE)
        }
    }


    private fun adjustToolbarVisibility(
        scrollY: Int,
        toolBar: MaterialToolbar,
        backBtn: MaterialButton,
        savedBtn: MaterialButton,
        sharedBtn: MaterialButton
    ) {
        val maxScroll = 950
        // Define start and end colors
        val iconStartColor = backBtn.iconTint?.defaultColor ?: Color.LTGRAY
        val iconEndColor = Color.BLACK
        val bgStartColor = backBtn.backgroundTintList?.defaultColor ?: Color.WHITE
        val bgEndColor = Color.WHITE

        // Calculate the fraction of the scroll (from 0 to 1)
        val fraction = (scrollY.coerceIn(0, maxScroll)).toFloat() / maxScroll
        toolBar.setBackgroundColor(
            Color.argb(
                (255 * fraction).toInt(),
                255, 255, 255
            )
        )
        if (scrollY > maxScroll) {
            toolBar.elevation = 20f
        } else {
            toolBar.elevation = 0f
        }
        Log.d("Fraction", "$fraction")

        // Calculate the blended color based on the fraction
        val iconBlendedColor = blendColors(Color.WHITE, Color.BLACK, fraction)
        val rippleBlendedColor = blendColors(Color.LTGRAY, Color.GRAY, fraction)
        val bgBlendedColor = blendColors(bgStartColor, bgEndColor, fraction)

        backBtn.background.setTint(bgBlendedColor)
        backBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        backBtn.iconTint = ColorStateList.valueOf(iconBlendedColor)


        Log.d("BlendedColor", "$iconBlendedColor")

        savedBtn.background.setTint(bgBlendedColor)
        savedBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        savedBtn.iconTint = ColorStateList.valueOf(iconBlendedColor)


        sharedBtn.background.setTint(bgBlendedColor)
        sharedBtn.rippleColor = ColorStateList.valueOf(rippleBlendedColor)
        sharedBtn.iconTint = ColorStateList.valueOf(iconBlendedColor)


    }

    private fun adjustBookMarkIcon(
        isBookMark: Boolean, savedBtn: MaterialButton,
        detailRecyclerView: RecyclerView
    ) {
        savedBookMarkState = Bundle().apply {
            this.putBoolean("isBookMarked", isBookMark)
        }

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
        outState.putBundle("isBookMarked", savedBookMarkState)
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