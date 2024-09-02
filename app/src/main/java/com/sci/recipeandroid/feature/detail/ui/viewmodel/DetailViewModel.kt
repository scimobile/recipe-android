package com.sci.recipeandroid.feature.detail.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeContainer
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterItem
import com.sci.recipeandroid.feature.detail.domain.model.Ingredients
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo
import com.sci.recipeandroid.feature.detail.ui.models.DetailSavedUiModel
import com.sci.recipeandroid.util.multiSelectBy
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailRepo: DetailRepo,
) : ViewModel() {
    private val detailScnData = MutableLiveData<DetailScreenState>()
    val detailData: LiveData<DetailScreenState> = detailScnData

    private val detailScnUpdateState = MutableLiveData<DetailScreenUpdateState>()
    val detailUpdateState: LiveData<DetailScreenUpdateState> = detailScnUpdateState

    private var ingredientList = mutableListOf<Ingredients>()

    private var detailFooterData = mutableListOf<DetailFooterItem>()

    private var bookMarkItemIdList = mutableListOf<Double>()

    private var detailId: Double? = null


    fun getDetailData(detailId: Double) {
        this.detailId = detailId
        viewModelScope.launch {
            detailScnData.value = DetailScreenState.Loading
            delay(3000)
            detailRepo.getDetail(detailId)
                .fold(
                    onSuccess = {
                        it.detailScreenData.onEach { data ->
                            if (data is DetailCenterContainer) {
                                ingredientList.addAll(data.ingredients)
                            }
                            if (data is DetailFooterContainer) {
                                detailFooterData.addAll(data.detailFooterItems)
                            }
                        }
                        detailScnData.value = DetailScreenState.Success(it)
                    },
                    onFailure = {
                        detailScnData.value = DetailScreenState.Error(it.message.toString())
                    }
                )
        }


    }

    fun onEvent(event: ScreenEvent) {
        when (event) {
            is ScreenEvent.UpdateServeAmt -> {
                ingredientList = ingredientList.map {
                    it.copy(amount = event.amount.toDouble() * it.amount)
                }.toMutableList()
                detailScnUpdateState.value =
                    DetailScreenUpdateState.IngredientUpdate(ingredientList)
            }

            is ScreenEvent.UpdateSavedItem -> {
                detailFooterData.onEach {
                    when (it) {
                        is CompleteMealContainer -> {
                            it.completeMealList = it.completeMealList.multiSelectBy(
                                selectedId = event.footerItemId,
                                selector = { completeMeal ->
                                    Pair(completeMeal.id, completeMeal.isBookmarked)
                                },
                                bind = { completeMeal, isSelect ->
                                    completeMeal.copy(isBookmarked = isSelect)
                                }
                            )
                        }

                        is AlsoLikeContainer -> {
                            it.alsoLikeList = it.alsoLikeList.multiSelectBy(
                                selectedId = event.footerItemId,
                                selector = { alsoLike ->
                                    Pair(alsoLike.id, alsoLike.isBookmarked)
                                },
                                bind = { alsoLike, isSelect ->
                                    alsoLike.copy(isBookmarked = isSelect)
                                }
                            )
                        }
                    }
                }
                if (bookMarkItemIdList.isEmpty()) {
                    bookMarkItemIdList.add(event.footerItemId)
                } else if (bookMarkItemIdList.contains(event.footerItemId)) {
                    bookMarkItemIdList.remove(event.footerItemId)
                } else {
                    bookMarkItemIdList.add(event.footerItemId)
                }
                Log.d("BookMarkItemList", "$bookMarkItemIdList")
                detailScnUpdateState.value = DetailScreenUpdateState.SavedItemUpdate(
                    DetailSavedUiModel(
                        detailIdList = bookMarkItemIdList.contains(detailId),
                        detailFooterContainer = DetailFooterContainer(detailFooterData)
                    )

                )
            }
        }
    }

    sealed class DetailScreenState {
        data object Loading : DetailScreenState()
        data class Success(val data: DetailDataContainer) : DetailScreenState()
        data class Error(val message: String) : DetailScreenState()
    }

    sealed class DetailScreenUpdateState {
        data class IngredientUpdate(val ingredientList: List<Ingredients>) :
            DetailScreenUpdateState()

        data class SavedItemUpdate(val detailSavedUiModel: DetailSavedUiModel) :
            DetailScreenUpdateState()
    }

    sealed class ScreenEvent {
        data class UpdateServeAmt(val amount: Int) : ScreenEvent()
        data class UpdateSavedItem(val footerItemId: Double) : ScreenEvent()
    }

}

