package com.sci.recipeandroid.feature.detail.ui.adapter.detailfooter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.sci.recipeandroid.databinding.DetailFooterContainerViewholderBinding

import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer

class DetailFooterContainerAdapter(val onClick: (Int) -> Unit) :
    RecyclerView.Adapter<DetailFooterContainerAdapter.DetailFooterContainerViewHolder>() {
    private var productList = emptyList<DetailFooterContainer>()
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
        return productList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(productLists: List<DetailFooterContainer>) {
        productList = productLists
        notifyDataSetChanged()
    }


    override fun onBindViewHolder(holder: DetailFooterContainerViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    inner class DetailFooterContainerViewHolder(private val binding: DetailFooterContainerViewholderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val recyclerView = binding.detailFooterContainerRv
        private val adapter = DetailFooterAdapter(onClick)
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