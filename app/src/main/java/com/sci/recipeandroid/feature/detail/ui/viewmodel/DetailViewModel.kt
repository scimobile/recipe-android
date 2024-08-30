package com.sci.recipeandroid.feature.detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailScreenData
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo

class DetailViewModel(
    private val detailRepo: DetailRepo,
) : ViewModel() {
    private val detailScnData = MutableLiveData<DetailDataContainer>()
    val detailData: LiveData<DetailDataContainer> = detailScnData

    init {
        getDetailCenter()
    }

    private fun getDetailCenter() {
        detailRepo.getDetail(1.0)
            .fold(
                onSuccess = {
                    detailScnData.value = it

                },
                onFailure = {

                }
            )

    }
}