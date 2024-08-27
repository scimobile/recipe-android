package com.sci.recipeandroid.feature.personalize.ui.adapter

import PersonalizeGoalsViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewPersonalizeGoalsBinding
import com.sci.recipeandroid.feature.personalize.ui.model.PersonalizeGoalsUiModel

class PersonalizeGoalsAdapter(
    private val onClickItem: (String?) -> Unit
) : RecyclerView.Adapter<PersonalizeGoalsViewHolder>() {

    private var list: List<PersonalizeGoalsUiModel> = emptyList()

    fun updateList(items: List<PersonalizeGoalsUiModel>) {
        list = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonalizeGoalsViewHolder {
        val binding = ItemViewPersonalizeGoalsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PersonalizeGoalsViewHolder(binding, onClickItem)
    }

    override fun onBindViewHolder(holder: PersonalizeGoalsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size
}
