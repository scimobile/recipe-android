package com.sci.recipeandroid.feature.detail.data.mapper

import com.sci.recipeandroid.feature.detail.data.model.Nutrition
import com.sci.recipeandroid.feature.detail.data.model.NutritionResponse
import com.sci.recipeandroid.feature.detail.domain.model.NutritionModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionScreenData
import com.sci.recipeandroid.feature.detail.domain.model.SubTypeNutritionModel

fun List<Nutrition>.toNutritionScreenData(): List<NutritionScreenData> {
    val tempList = mutableListOf<NutritionScreenData>()
    this.onEach {
        tempList.add(
            NutritionModel(
                id = it.id,
                typeName = it.typeName,
                dailyValue = it.dailyValue,
                amount = it.amount
            )
        )
        it.subType.map { subType ->
            tempList.add(
                SubTypeNutritionModel(
                    id = subType.id,
                    subName = subType.typeName,
                    subAmount = subType.subAmount,
                    subDailyValue = subType.dailyValue
                )
            )
        }
    }
    return tempList
}