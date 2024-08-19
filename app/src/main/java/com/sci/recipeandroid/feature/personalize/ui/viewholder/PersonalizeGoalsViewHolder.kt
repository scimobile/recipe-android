import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sci.recipeandroid.R
import com.sci.recipeandroid.databinding.ItemViewPersonalizeGoalsBinding
import com.sci.recipeandroid.feature.personalize.ui.model.PersonalizeGoalsUiModel

class PersonalizeGoalsViewHolder(
    private val binding: ItemViewPersonalizeGoalsBinding,
    private val onClickItem: (String?) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(personalizeGoalsUiModel: PersonalizeGoalsUiModel) {
        binding.tvName.text = personalizeGoalsUiModel.personalizeGoalsModel.name
        Glide.with(binding.ivIcon.context)
            .load(personalizeGoalsUiModel.personalizeGoalsModel.iconUrl)
            .into(binding.ivIcon)

        if (personalizeGoalsUiModel.isSelect) {
            binding.cardPersonalizeGoal.strokeColor = ContextCompat.getColor(itemView.context, R.color.color_primary)
            binding.tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_text_primary))
        } else {
            binding.cardPersonalizeGoal.strokeColor = ContextCompat.getColor(itemView.context, R.color.color_surface)
            binding.tvName.setTextColor(ContextCompat.getColor(itemView.context, R.color.color_text_secondary))
        }

        binding.root.setOnClickListener {
            onClickItem(personalizeGoalsUiModel.personalizeGoalsModel.id)
        }
    }
}
