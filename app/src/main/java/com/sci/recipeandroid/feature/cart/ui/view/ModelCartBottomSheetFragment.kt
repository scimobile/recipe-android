package com.sci.recipeandroid.feature.cart.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.LayoutBottomSheetBinding
import com.sci.recipeandroid.feature.cart.ui.model.GroupedIngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class ModelCartBottomSheetFragment: BottomSheetDialogFragment() {

    private var groupedIngredients: MutableList<GroupedIngredientUiModel>? = null
    fun setGroupedIngredients(groupedIngredients: MutableList<GroupedIngredientUiModel>) {
        this.groupedIngredients = groupedIngredients
    }

    private var _binding: LayoutBottomSheetBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddToCartViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutDeleteList.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_App_MaterialAlertDialog)
                .setTitle("Are You Sure?")
                .setMessage("All recipe and item will be removed from your current list.")
                .setNegativeButton("CANCEL") { dialog, which ->
                    dismiss()
                }
                .setPositiveButton("YES") { dialog, which ->
                    viewModel.deleteAllRecipe()
                    dismiss()
                }
                .show()
        }
        binding.layoutShareList.setOnClickListener {
            shareItemDetails()
        }
    }

    private fun shareItemDetails() {

        val message = buildString {
            append("Here's my grocery list:\n\n")
            groupedIngredients?.forEach { categoryWithIngredients ->
                if (categoryWithIngredients.category != "Done"){
                    append("# ${categoryWithIngredients.category}\n")
                    categoryWithIngredients.ingredients.forEach { ingredient ->
                        append(" - ${ingredient.amount} ${ingredient.name}\n")
                    }
                    append("\n")
                }
            }
        }
        
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, message)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share your list via:")
        startActivity(shareIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}