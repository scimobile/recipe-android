package com.sci.recipeandroid.feature.cart.ui.screen

import GroupedIngredientAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.R
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.sci.recipeandroid.databinding.FragmentCartBinding
import com.sci.recipeandroid.feature.cart.ui.adapter.ParentRecipeAdapter
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartUiState
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartViewModel


class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!

    private var recipeAdapter: ParentRecipeAdapter? = null
    private var groupedIngredientAdapter: GroupedIngredientAdapter? = null

    private val viewModel: AddToCartViewModel by viewModel()


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

        appBarDecoration()
        adapterSetUp()
        observeViewModel()
    }

    private fun adapterSetUp() {
        recipeAdapter = ParentRecipeAdapter()
        groupedIngredientAdapter = GroupedIngredientAdapter(viewModel)
        val concatAdapter = ConcatAdapter(recipeAdapter, groupedIngredientAdapter)

        binding.concatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.concatRecyclerView.adapter = concatAdapter
    }

    private fun observeViewModel() {
        viewModel.fetchRecipes()
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AddToCartUiState.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                is AddToCartUiState.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE

                    recipeAdapter?.submitData(
                        state.recipes,
                        state.groupedIngredients.values.sumOf { it.size }
                    )
                    groupedIngredientAdapter?.submitData(
                        recipes =  state.recipes,
                        groupedIngredients =  state.groupedIngredients
                    )
                }
                is AddToCartUiState.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                }

                is AddToCartUiState.UpdateSuccess -> {
                    recipeAdapter?.submitData(
                        state.recipes,
                        state.groupedIngredients.values.sumOf { it.size }
                    )
                    groupedIngredientAdapter?.submitData(
                        recipes =  state.recipes,
                        groupedIngredients =  state.groupedIngredients
                    )
                }
            }
        }
    }

    private fun appBarDecoration() {
        binding.appBarLayout.setExpanded(true, true)
        binding.toolBar.setBackgroundResource(R.color.white)
        binding.appBarLayout.setBackgroundResource(R.color.white)

        binding.concatRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
    }
    


}