package com.sci.recipeandroid.feature.detail.ui.viewmodel

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
import com.sci.recipeandroid.feature.detail.domain.model.IngredientsModel
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo
import com.sci.recipeandroid.feature.detail.ui.models.DetailSavedUiModel
import com.sci.recipeandroid.util.SingleLiveEvent
import com.sci.recipeandroid.util.multiSelectBy
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DetailViewModel(
    private val detailRepo: DetailRepo,
) : ViewModel() {
    private val _detailScnState = MutableLiveData<DetailScreenState>()
    val detailScnState: LiveData<DetailScreenState> = _detailScnState

    private val _detailScnUpdateState = MutableLiveData<DetailScreenUpdateState>()
    val detailUpdateState: LiveData<DetailScreenUpdateState> = _detailScnUpdateState

    private val _detailNavigation = SingleLiveEvent<DetailNavigation>()
    val detailNavigation: LiveData<DetailNavigation> = _detailNavigation

    private var ingredientList = mutableListOf<IngredientsModel>()

    private var detailFooterData = mutableListOf<DetailFooterItem>()

    private var bookMarkItemIdList = mutableListOf<Double>()

    var detailId: Double? = null

    //    init {
//        detailId?.let {
//            getDetailData(it)
//        }
//    }
    fun getDetailData(detailId: Double) {
        this.detailId = detailId
        viewModelScope.launch {
            _detailScnState.value = DetailScreenState.Loading
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
                        _detailScnState.value = DetailScreenState.Success(it)
                    },
                    onFailure = {
                        _detailScnState.value = DetailScreenState.Error(it.message.toString())
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
                _detailScnUpdateState.value =
                    DetailScreenUpdateState.IngredientUpdate(ingredientList)
            }

            is ScreenEvent.UpdateSavedItem -> {
                detailFooterData.onEach {
                    when (it) {
                        is CompleteMealContainer -> {
                            it.completeMealModelList = it.completeMealModelList.multiSelectBy(
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
                            it.alsoLikeModelList = it.alsoLikeModelList.multiSelectBy(
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
                //adding the id of the bookmarked item if necessary
                if (bookMarkItemIdList.isEmpty()) {
                    bookMarkItemIdList.add(event.footerItemId)
                } else if (bookMarkItemIdList.contains(event.footerItemId)) {
                    bookMarkItemIdList.remove(event.footerItemId)
                } else {
                    bookMarkItemIdList.add(event.footerItemId)
                }

                _detailScnUpdateState.value = DetailScreenUpdateState.SavedItemUpdate(
                    DetailSavedUiModel(
                        detailIdList = bookMarkItemIdList.contains(detailId),
                        detailFooterContainer = DetailFooterContainer(detailFooterData)
                    )
                )
            }

            is ScreenEvent.NavigateToDirection -> {
                _detailNavigation.value = DetailNavigation.NavigateToDirection(event.id)
            }

            is ScreenEvent.NavigateToNutrition -> {
                _detailNavigation.value = DetailNavigation.NavigateToNutrition(event.id)
            }
        }
    }

    sealed class DetailScreenState {
        data object Loading : DetailScreenState()
        data class Success(
            val data: DetailDataContainer,
        ) : DetailScreenState()

        data class Error(val message: String) : DetailScreenState()


    }

    sealed class DetailScreenUpdateState {
        data class IngredientUpdate(val ingredientList: List<IngredientsModel>) :
            DetailScreenUpdateState()

        data class SavedItemUpdate(val detailSavedUiModel: DetailSavedUiModel) :
            DetailScreenUpdateState()
    }

    sealed class DetailNavigation {
        data class NavigateToNutrition(val id: Double) : DetailNavigation()
        data class NavigateToDirection(val id: Double) : DetailNavigation()
    }

    sealed class ScreenEvent {
        data class UpdateServeAmt(val amount: Int) : ScreenEvent()
        data class UpdateSavedItem(val footerItemId: Double) : ScreenEvent()
        data class NavigateToNutrition(val id: Double) : ScreenEvent()
        data class NavigateToDirection(val id: Double) : ScreenEvent()
    }

}

