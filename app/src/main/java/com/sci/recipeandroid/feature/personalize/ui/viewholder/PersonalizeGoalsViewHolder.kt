import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewPersonalizeGoalsBinding
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel

class PersonalizeGoalsViewHolder(
    private val binding: ItemViewPersonalizeGoalsBinding,
    private val onClickItem: (PersonalizeGoalsModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PersonalizeGoalsModel, isSelected: Boolean) {
        binding.tvName.text = item.name
        Glide.with(binding.ivIcon.context)
            .load(item.iconUrl)
            .into(binding.ivIcon)

        if (isSelected) {
            binding.cardPersonalizeGoal.strokeColor = ContextCompat.getColor(itemView.context, R.color.color_primary)
            binding.tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_text_primary))
        } else {
            binding.cardPersonalizeGoal.strokeColor = ContextCompat.getColor(itemView.context, R.color.color_surface)
            binding.tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_text_secondary))
        }

        binding.root.setOnClickListener {
            onClickItem(item)
        }
    }
}
