package com.sci.recipeandroid.feature.onboarding.data

import com.sci.recipeandroid.R
import com.sci.recipeandroid.feature.onboarding.ui.model.OnBoardUiModel

class OnBoardListProvider {
    fun getOnBoardList(): List<OnBoardUiModel> {
        return listOf(
            OnBoardUiModel(
                title = "Personalized Recipe Discovery",
                description = "Tell us your food preferences and we’ll only serve you delicious recipes ideas.",
                imagePath = R.drawable.onboard1
            ),
            OnBoardUiModel(
                title = "Never Forget an Ingredient",
                description = "Build your weekly grocery list and stay organized while you shop.",
                imagePath = R.drawable.onboard2
            ),
            OnBoardUiModel(
                title = "Your Favourite Recipes at Your Fingertips",
                description = "Save time on planning by having your favourite recipes always within reach.",
                imagePath = R.drawable.onboard3
            )
        )
    }
}
