package com.sci.recipeandroid.feature.detail.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.ItemViewDetailHeaderBinding
import com.sci.recipeandroid.feature.detail.domain.model.DetailHeaderContainerModel
import com.sci.recipeandroid.feature.detail.ui.viewholder.detail.DetailHeaderViewHolder

class DetailHeaderAdapter(val onClick:(Int)-> Unit) : RecyclerView.Adapter<DetailHeaderViewHolder>() {
    private var headerContainerList = emptyList<DetailHeaderContainerModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailHeaderViewHolder {
        ItemViewDetailHeaderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return DetailHeaderViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return headerContainerList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(dataLists: List<DetailHeaderContainerModel>) {
        headerContainerList = dataLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DetailHeaderViewHolder, position: Int) {
        holder.bind(headerContainerList[position])
    }
}