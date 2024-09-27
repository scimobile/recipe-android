import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.project.addtocart.adapter.IngredientAdapter
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.ui.model.GroupedIngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel

class GroupedIngredientAdapter(
    private val onCheckBoxClick: (IngredientUiModel) -> Unit
) : ListAdapter<GroupedIngredientUiModel, GroupedIngredientAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    private var recipes: List<RecipeUiModel> = emptyList()

    fun submitData(
        groupedIngredients: List<GroupedIngredientUiModel>,
        recipes: List<RecipeUiModel>
    ) {
        this.recipes = recipes
        submitList(groupedIngredients)
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val categoryTitleTextView: TextView = view.findViewById(R.id.categoryTitle)
        private val ingredientRecyclerView: RecyclerView = view.findViewById(R.id.ingredientRecyclerView)

        fun bind(groupedIngredientUiModel: GroupedIngredientUiModel) {
            categoryTitleTextView.text = groupedIngredientUiModel.category
            setUpIngredientRecycler(groupedIngredientUiModel.ingredients)
        }

        private fun setUpIngredientRecycler(ingredients: List<IngredientUiModel>) {
            val ingredientAdapter = IngredientAdapter(recipes, onCheckBoxClick = onCheckBoxClick)
            ingredientAdapter.submitList(ingredients)
            ingredientRecyclerView.adapter = ingredientAdapter
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_ingredient_group, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryWithIngredients = getItem(position)
        holder.bind(categoryWithIngredients)
    }
}



class CategoryDiffCallback : DiffUtil.ItemCallback<GroupedIngredientUiModel>() {
    override fun areItemsTheSame(
        oldItem: GroupedIngredientUiModel,
        newItem: GroupedIngredientUiModel
    ): Boolean {
        return oldItem.category == newItem.category
    }

    override fun areContentsTheSame(
        oldItem: GroupedIngredientUiModel,
        newItem: GroupedIngredientUiModel
    ): Boolean {
        return oldItem == newItem
    }
}
