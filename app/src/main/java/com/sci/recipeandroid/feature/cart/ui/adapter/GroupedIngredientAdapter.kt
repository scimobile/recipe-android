import android.annotation.SuppressLint
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.project.addtocart.adapter.IngredientAdapter
import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.cart.ui.model.IngredientUiModel
import com.sci.recipeandroid.feature.cart.ui.model.RecipeUiModel
import com.sci.recipeandroid.feature.cart.ui.viewmodel.AddToCartViewModel

class GroupedIngredientAdapter(
    private val viewModel: AddToCartViewModel,
) : ListAdapter<CategoryWithIngredients, GroupedIngredientAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    private var recipes: List<RecipeUiModel> = emptyList()

    fun submitData(groupedIngredients: Map<String, List<IngredientUiModel>>, recipes: List<RecipeUiModel>) {
        this.recipes = recipes
        val categoryWithIngredients = groupedIngredients.map { (key, value) -> CategoryWithIngredients(key, value) }
        submitList(categoryWithIngredients)
    }

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val categoryTitleTextView: TextView = view.findViewById(R.id.categoryTitle)
        private val ingredientRecyclerView: RecyclerView = view.findViewById(R.id.ingredientRecyclerView)

        fun bind(categoryWithIngredients: CategoryWithIngredients) {
            categoryTitleTextView.text = categoryWithIngredients.category
            setUpIngredientRecycler(categoryWithIngredients.ingredients)
        }

        private fun setUpIngredientRecycler(ingredients: List<IngredientUiModel>) {
            val ingredientToRecipeMap = createIngredientToRecipeMap(recipes)
            val ingredientAdapter = IngredientAdapter(ingredientToRecipeMap)

            ingredientAdapter.submitData(ingredients)
            ingredientRecyclerView.adapter = ingredientAdapter
            ingredientRecyclerView.layoutManager = LinearLayoutManager(itemView.context)

            ingredientAdapter.onCheckBoxClick = {
                viewModel.updateIngredientCheckedStatus(
                    ingredientId = it.ingredientId,
                    checked = it.checked
                )
            }
        }
    }

    private fun createIngredientToRecipeMap(recipes: List<RecipeUiModel>): Map<String, List<String>> {
        return recipes.flatMap { recipe ->
            recipe.items.map { it.ingredientId to recipe.title }
        }.groupBy({ it.first }, { it.second })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_category_group, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryWithIngredients = getItem(position)
        holder.bind(categoryWithIngredients)
    }
}

data class CategoryWithIngredients(
    val category: String,
    val ingredients: List<IngredientUiModel>
)

class CategoryDiffCallback : DiffUtil.ItemCallback<CategoryWithIngredients>() {
    override fun areItemsTheSame(
        oldItem: CategoryWithIngredients,
        newItem: CategoryWithIngredients
    ): Boolean {
        return oldItem.category == newItem.category
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: CategoryWithIngredients,
        newItem: CategoryWithIngredients
    ): Boolean {
        // Check if the categories and their ingredient lists are the same
        return oldItem.category == newItem.category && oldItem.ingredients == newItem.ingredients
    }
}
