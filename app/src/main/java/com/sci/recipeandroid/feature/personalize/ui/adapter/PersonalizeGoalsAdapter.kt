package com.sci.recipeandroid.feature.personalize.ui.adapter

import PersonalizeGoalsViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewPersonalizeGoalsBinding
import com.sci.recipeandroid.feature.personalize.domain.model.PersonalizeGoalsModel

class PersonalizeGoalsAdapter(
    private val onClickItem: (PersonalizeGoalsModel) -> Unit
) : RecyclerView.Adapter<PersonalizeGoalsViewHolder>() {

    private var items: List<PersonalizeGoalsModel> = emptyList()
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalizeGoalsViewHolder {
        val binding = ItemViewPersonalizeGoalsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonalizeGoalsViewHolder(binding) { item ->
            val position = items.indexOf(item)
            val previouslySelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previouslySelectedPosition)
            notifyItemChanged(selectedPosition)
            onClickItem(item)
        }
    }

    override fun onBindViewHolder(holder: PersonalizeGoalsViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item, position == selectedPosition)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<PersonalizeGoalsModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}
