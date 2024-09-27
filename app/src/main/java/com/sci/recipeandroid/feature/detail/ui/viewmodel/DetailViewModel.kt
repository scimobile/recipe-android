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
import com.sci.recipeandroid.feature.detail.domain.repository.DetailRepo
import com.sci.recipeandroid.feature.detail.ui.models.DetailCenterUiModels
import com.sci.recipeandroid.feature.detail.ui.models.DetailSavedUiModels
import com.sci.recipeandroid.feature.detail.ui.models.IngredientUiModels
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

    private var detailCenterUiModelsList = mutableListOf<DetailCenterUiModels>()

    private var detailFooterData = mutableListOf<DetailFooterItem>()

    private var bookMarkItemIdList = mutableListOf<Double>()

    private var addToCartList = mutableListOf<Double>()

    private var detailId: Double? = null
    private val amount: Int? = null

    fun getDetailData(detailId: Double) {
        this.detailId = detailId
        viewModelScope.launch {
            _detailScnState.value = DetailScreenState.Loading
            delay(3000)
            detailRepo.getDetail(detailId)
                .fold(
                    onSuccess = { result ->
                        val data = DetailDataContainer(
                            detailScreenData = result.detailScreenData.map { data ->
                                when (data) {

                                    is DetailCenterContainer -> {
                                        val detailCenterData = DetailCenterUiModels(
                                            serveAmt = 1,
                                            selectedUnit = "us",
                                            isAddedToCart = false,
                                            nutritionPerServeModel = data.nutritionPerServeModel,
                                            ingredients = data.ingredients.map { ingredientsModel ->
                                                IngredientUiModels(
                                                    ingredientModel = ingredientsModel,
                                                    selectedUnit = "us"
                                                )
                                            }
                                        )
                                        detailCenterUiModelsList.add(detailCenterData)
                                        detailCenterData
                                    }

                                    is DetailFooterContainer -> {
                                        detailFooterData.addAll(data.detailFooterItems)
                                        data
                                    }

                                    else -> data
                                }
                            }
                        )
                        _detailScnState.value = DetailScreenState.Success(data)
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
                detailCenterUiModelsList = detailCenterUiModelsList.map { data ->
                    data.copy(
                        serveAmt = event.amount,
                        ingredients = data.ingredients.map {
                            it.ingredientModel.amountPerUsUnit =
                                it.ingredientModel.amountPerUsUnit.copy(
                                    usAmt = (it.ingredientModel.increaseRate) +
                                            it.ingredientModel.amountPerUsUnit.usAmt
                                )
                            it.ingredientModel.amountPerMetricUnit =
                                it.ingredientModel.amountPerMetricUnit.copy(
                                    metricAmt = (it.ingredientModel.increaseRate) +
                                            it.ingredientModel.amountPerMetricUnit.metricAmt
                                )
                            it
                        }
                    )
                }.toMutableList()

                _detailScnUpdateState.value =
                    DetailScreenUpdateState.DetailCenterUpdate(
                        detailCenterUiModelsList,
                        isAddedToCart = addToCartList.contains(detailId),
                        isBookMarked = bookMarkItemIdList.contains(detailId)
                    )
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

                //region adding the id of the bookmarked item to post to the server
                if (bookMarkItemIdList.isEmpty()) {
                    bookMarkItemIdList.add(event.footerItemId)
                } else if (bookMarkItemIdList.contains(event.footerItemId)) {
                    bookMarkItemIdList.remove(event.footerItemId)
                } else {
                    bookMarkItemIdList.add(event.footerItemId)
                }
                //endregion
                Log.wtf("CenterStateData", bookMarkItemIdList.toString())

                _detailScnUpdateState.value = DetailScreenUpdateState.SavedItemUpdate(
                    DetailSavedUiModels(
                        isBookMarked = bookMarkItemIdList.contains(detailId),
                        detailFooterContainer = DetailFooterContainer(detailFooterData)
                    ),
                    detailCenterUiModels = detailCenterUiModelsList,
                    isAddedToCart = addToCartList.contains(detailId)
                )
            }

            is ScreenEvent.NavigateToDirection -> {
                _detailNavigation.value = DetailNavigation.NavigateToDirection(event.id)
            }

            is ScreenEvent.NavigateToNutrition -> {
                _detailNavigation.value = DetailNavigation.NavigateToNutrition(event.id)
            }

            is ScreenEvent.AddToCart -> {
                detailFooterData.onEach {
                    when (it) {
                        is CompleteMealContainer -> {
                            it.completeMealModelList = it.completeMealModelList.multiSelectBy(
                                selectedId = event.recipeId,
                                selector = { completeMeal ->
                                    Pair(completeMeal.id, completeMeal.isAddedToCart)
                                },
                                bind = { completeMeal, isSelect ->
                                    completeMeal.copy(isAddedToCart = isSelect)
                                }
                            )
                        }

                        is AlsoLikeContainer -> {
                            it.alsoLikeModelList = it.alsoLikeModelList.multiSelectBy(
                                selectedId = event.recipeId,
                                selector = { alsoLike ->
                                    Pair(alsoLike.id, alsoLike.isAddedToCart)
                                },
                                bind = { alsoLike, isSelect ->
                                    alsoLike.copy(isAddedToCart = isSelect)
                                }
                            )
                        }
                    }
                }

                //region adding the id of the added to cart item to post to the server
                if (addToCartList.isEmpty()) {
                    addToCartList.add(event.recipeId)
                } else if (addToCartList.contains(event.recipeId)) {
                    addToCartList.remove(event.recipeId)
                } else {
                    addToCartList.add(event.recipeId)
                }
                //endregion
                detailCenterUiModelsList = detailCenterUiModelsList.map {
                    it.copy(
                        isAddedToCart = addToCartList.contains(detailId)
                    )
                }.toMutableList()

                _detailScnUpdateState.value = DetailScreenUpdateState.AddToCartUpdate(
                    DetailFooterContainer(detailFooterData),
                    detailCenterUiModels = detailCenterUiModelsList,
                    isAddedToCart = addToCartList.contains(detailId),
                    isBookMarked = bookMarkItemIdList.contains(detailId)
                )
            }

            is ScreenEvent.UpdateUnit -> {
                detailCenterUiModelsList = detailCenterUiModelsList.map {
                    it.copy(
                        isAddedToCart = addToCartList.contains(detailId),
                        ingredients = it.ingredients.map { ingredientUiModels ->
                            ingredientUiModels.copy(selectedUnit = event.unit)
                        },
                        selectedUnit = event.unit
                    )
                }.toMutableList()

                _detailScnUpdateState.value =
                    DetailScreenUpdateState.DetailCenterUpdate(
                        detailCenterUiModelsList,
                        isAddedToCart = addToCartList.contains(detailId),
                        isBookMarked = bookMarkItemIdList.contains(detailId)
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
        data class DetailCenterUpdate(
            val detailCenterDataList: List<DetailCenterUiModels>,
            val isAddedToCart: Boolean,
            val isBookMarked: Boolean
        ) : DetailScreenUpdateState()

        data class SavedItemUpdate(
            val detailSavedUiModel: DetailSavedUiModels,
            val detailCenterUiModels: List<DetailCenterUiModels>,
            val isAddedToCart: Boolean
        ) : DetailScreenUpdateState()

        data class AddToCartUpdate(
            val detailFooterContainer: DetailFooterContainer,
            val detailCenterUiModels: List<DetailCenterUiModels>,
            val isAddedToCart: Boolean,
            val isBookMarked: Boolean
        ) : DetailScreenUpdateState()
    }

    sealed class DetailNavigation {
        data class NavigateToNutrition(val id: Double) : DetailNavigation()
        data class NavigateToDirection(val id: Double) : DetailNavigation()
    }

    sealed class ScreenEvent {
        data class UpdateServeAmt(val amount: Int) : ScreenEvent()
        data class UpdateSavedItem(val footerItemId: Double) : ScreenEvent()
        data class UpdateUnit(val unit: String) : ScreenEvent()
        data class AddToCart(val recipeId: Double) : ScreenEvent()
        data class NavigateToNutrition(val id: Double) : ScreenEvent()
        data class NavigateToDirection(val id: Double) : ScreenEvent()
    }

}

