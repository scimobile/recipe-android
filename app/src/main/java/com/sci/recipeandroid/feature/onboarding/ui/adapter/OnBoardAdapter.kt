package com.sci.recipeandroid.feature.onboarding.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewOnboardBinding
import com.sci.recipeandroid.feature.onboarding.ui.model.OnBoardModel
import com.sci.recipeandroid.feature.onboarding.ui.viewholder.OnBoardViewHolder

class OnBoardAdapter: RecyclerView.Adapter<OnBoardViewHolder>() {
    private var List = emptyList<OnBoardModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        val binding = ItemViewOnboardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return OnBoardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(
            List[position]
        )
    }

    fun updateList(list: List<OnBoardModel>) {
        List = list
        notifyDataSetChanged()
    }
}