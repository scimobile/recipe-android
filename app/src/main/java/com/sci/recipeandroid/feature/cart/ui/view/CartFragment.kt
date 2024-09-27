package com.sci.recipeandroid.feature.cart.ui.view


import GroupedIngredientAdapter
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.addtocart.adapter.RecipeAdapter
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.FragmentCartBinding
import com.sci.recipeandroid.feature.cart.ui.adapter.RecipeContainerAdapter
import com.sci.recipeandroid.feature.cart.ui.model.GroupedIngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeContainerUiModel
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartUiEvent
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartUiState
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private var parentRecipeAdapter: RecipeContainerAdapter? = null
    private var childRecipeAdapter: RecipeAdapter? = null
    private var groupedIngredientAdapter: GroupedIngredientAdapter? = null

    private var modelCartBottomSheetFragment: ModelCartBottomSheetFragment? = null

    private val viewModel: AddToCartViewModel by activityViewModel()

    private val groupedIngredients: MutableList<GroupedIngredientUiModel> = mutableListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setOnApplyWindowInsetsListener(binding.coordinatorLayout) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                topMargin = insets.top
            }
            WindowInsetsCompat.CONSUMED
        }
        val window = requireActivity().window
        window.statusBarColor = Color.WHITE
        window.navigationBarColor = Color.TRANSPARENT

        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true

        setUpTopAppBar()
        setUpAdapter()
        observeViewModel()
    }

    private fun setUpAdapter() {
        Log.d("CartFragment", "SetUpAdapter")
        if (childRecipeAdapter == null) {
            childRecipeAdapter = RecipeAdapter(
                onItemDelete = {
                    viewModel.deleteRecipe(it.recipeId)
                },
                onServingSizeChange = {
                    viewModel.updateServingSize(it.recipeId, it.servingCount)
                }
            )
            parentRecipeAdapter = RecipeContainerAdapter(childRecipeAdapter!!)
        }

        groupedIngredientAdapter = GroupedIngredientAdapter(
            onCheckBoxClick = {
                viewModel.updateIngredientCheckedStatus(
                    ingredientId = it.ingredientId,
                    checked = it.checked
                )
            }
        )
        val concatAdapter = ConcatAdapter(parentRecipeAdapter, groupedIngredientAdapter)
        binding.rvCart.adapter = concatAdapter

    }

    private fun observeViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AddToCartUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvCart.visibility = View.INVISIBLE
                }
                is AddToCartUiState.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    binding.rvCart.visibility = View.VISIBLE
                    setUpEmptyState(state)

                    //Recipe Part
                    parentRecipeAdapter?.submitList(
                        listOf(
                            RecipeContainerUiModel(
                                recipes = state.recipes,
                                ingredientSize = state.groupedIngredients.sumOf { it.ingredients.size },
                                recipeSize = state.recipes.size
                            )
                        )
                    )

                    //Ingredient Part
                    groupedIngredientAdapter?.submitData(
                        recipes =  state.recipes,
                        groupedIngredients = state.groupedIngredients
                    )

                    //for sharing unfinished ingredients via social media
                    groupedIngredients.clear()
                    groupedIngredients.addAll(state.groupedIngredients)
                }

            }
        }
        viewModel.uiEvent.observe(viewLifecycleOwner) {event ->
            when(event) {
                is AddToCartUiEvent.Error -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setUpEmptyState(state: AddToCartUiState.Success) {
        if (state.recipes.isEmpty()){
            binding.tvEmptyText.visibility = View.VISIBLE
            binding.rvCart.setBackgroundResource(R.color.white)
        } else {
            binding.tvEmptyText.visibility = View.INVISIBLE
        }
    }

    private fun setUpTopAppBar() {
        binding.appBarLayout.setExpanded(true, true)
        binding.toolBar.setBackgroundResource(R.color.white)
        binding.appBarLayout.setBackgroundResource(R.color.black)

        binding.rvCart.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // Show the title as soon as scrolling starts
                if (recyclerView.canScrollVertically(-1)) {
                    binding.toolBar.title = "My Groceries"
                } else {
                    binding.toolBar.title = ""
                }
            }
        })

        binding.toolBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.more ->{
                    setUpBottomSheet()
                    true
                }
                else ->{
                    false
                }
            }
        }
    }

    private fun setUpBottomSheet() {
        modelCartBottomSheetFragment = ModelCartBottomSheetFragment()
        modelCartBottomSheetFragment?.setGroupedIngredients(groupedIngredients)
        modelCartBottomSheetFragment?.show(childFragmentManager, ModelCartBottomSheetFragment.TAG)
    }

}