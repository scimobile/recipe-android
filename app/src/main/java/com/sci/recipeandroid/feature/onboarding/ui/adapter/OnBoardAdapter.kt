package com.sci.recipeandroid.feature.onboarding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewOnboardBinding
import com.sci.recipeandroid.feature.onboarding.ui.model.OnBoardUiModel
import com.sci.recipeandroid.feature.onboarding.ui.viewholder.OnBoardViewHolder

class OnBoardAdapter: RecyclerView.Adapter<OnBoardViewHolder>() {
    private var list = emptyList<OnBoardUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        val binding = ItemViewOnboardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OnBoardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(
            list[position]
        )
    }

    fun updateList(list: List<OnBoardUiModel>) {
        this.list = list
        notifyDataSetChanged()
    }
}