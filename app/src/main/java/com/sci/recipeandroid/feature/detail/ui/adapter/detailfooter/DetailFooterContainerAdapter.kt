package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sci.recipeandroid.databinding.DetailFooterContainerViewholderBinding

import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer

class DetailFooterContainerAdapter(
    private val onCompleteMealSaved: (Double) -> Unit,
    private val onAlsoLikeSaved: (Double) -> Unit
) : RecyclerView.Adapter<DetailFooterContainerAdapter.DetailFooterContainerViewHolder>() {

    private var dataList = emptyList<DetailFooterContainer>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailFooterContainerViewHolder {
        DetailFooterContainerViewholderBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            return DetailFooterContainerViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(dataList: List<DetailFooterContainer>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DetailFooterContainerViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    inner class DetailFooterContainerViewHolder(private val binding: DetailFooterContainerViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val recyclerView = binding.detailFooterContainerRv
        private val adapter = DetailFooterAdapter(
            onCompleteMealClick = onCompleteMealSaved,
            onAlsoLikeClick = onAlsoLikeSaved
        )

        init {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(
                binding.root.context, LinearLayoutManager.VERTICAL, false
            )
        }

        fun bind(data: DetailFooterContainer) {
            adapter.updateList(data.detailFooterItems)
        }
    }
}