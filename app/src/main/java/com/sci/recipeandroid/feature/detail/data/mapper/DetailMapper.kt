package com.sci.recipeandroid.feature.detail.data.mapper

import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeModel
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeContainer
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealModel
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailCenterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailDataContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailFooterContainer
import com.sci.recipeandroid.feature.detail.domain.model.DetailHeaderContainerModel
import com.sci.recipeandroid.feature.detail.domain.model.IngredientsModel
import com.sci.recipeandroid.feature.detail.domain.model.NutritionPerServeModel
import com.sci.recipeandroid.feature.detail.domain.model.RecipeOwnerContainer

fun String.toDetailModel(): DetailDataContainer {
    return DetailDataContainer(
        detailScreenData = listOf(
            DetailHeaderContainerModel(
                recipeImg = "https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1",
                recipeName = "Chocolate Chip Cookies",
                recipeOwner = "Chef John Doe",
                ratingPoint = 4.1f,
                ratingCount = "123",
                isCommunityPick = true,
                description = "These classic chocolate chip cookies are soft, chewy, and full of delicious chocolate chips.",
                estimatedTime = "30 minutes",
                id = 1.0
            ),
            DetailCenterContainer(
                ingredients = listOf(
                    IngredientsModel(
                        name = "Flour",
                        amount = 1.0,
                        amountUnit = "cup",
                        increaseRate = "1.5x",
                        image = "flour_image_url"
                    ),
                    IngredientsModel(
                        name = "Sugar",
                        amount = 0.5,
                        amountUnit = "cup",
                        increaseRate = "2x",
                        image = "sugar_image_url"
                    ),
                    IngredientsModel(
                        name = "Flour",
                        amount = 1.0,
                        amountUnit = "cup",
                        increaseRate = "1.5x",
                        image = "flour_image_url"
                    ),
                    IngredientsModel(
                        name = "Sugar",
                        amount = 0.5,
                        amountUnit = "cup",
                        increaseRate = "2x",
                        image = "sugar_image_url"
                    ),
                    IngredientsModel(
                        name = "Eggs",
                        amount = 2.0,
                        amountUnit = "",
                        increaseRate = "1x",
                        image = "egg_image_url"
                    ),
                    IngredientsModel(
                        name = "Butter",
                        amount = 0.25,
                        amountUnit = "cup",
                        increaseRate = "1.25x", image = "butter_image_url"
                    ),
                    IngredientsModel(
                        name = "Milk",
                        amount = 1.0,
                        amountUnit = "cup",
                        increaseRate = "1x",
                        image = "milk_image_url"
                    ),
                    IngredientsModel(
                        name = "Baking Powder",
                        amount = 1.5,
                        amountUnit = "tsp",
                        increaseRate = "1x",
                        image = "baking_powder_image_url"
                    ),
                    IngredientsModel(
                        name = "Vanilla Extract",
                        amount = 1.0,
                        amountUnit = "tsp",
                        increaseRate = "1x",
                        image = "vanilla_extract_image_url"
                    ),
                    IngredientsModel(
                        name = "Chocolate Chips",
                        amount = 1.0,
                        amountUnit = "cup",
                        increaseRate = "1.5x",
                        image = "chocolate_chips_image_url"
                    ),
                    IngredientsModel(
                        name = "Salt",
                        amount = 0.25,
                        amountUnit = "tsp", increaseRate = "1x",
                        image = "salt_image_url"
                    ),
                    IngredientsModel(
                        name = "Cinnamon",
                        amount = 1.0,
                        amountUnit = "tsp",
                        increaseRate = "0.5x",
                        image = "cinnamon_image_url"
                    )
                ),
                nutritionPerServeModel = NutritionPerServeModel(
                    calories = "317.0",
                    carbs = "125.4g",
                    protein = "35.6g",
                    fat = "25.6g"
                ),
            ),
            DetailFooterContainer(
                detailFooterItems = listOf(
                    RecipeOwnerContainer(
                        recipeOwnerName = "Chef John Doe",
                        recipeOwnerImg = "https://example.com/chef_image.jpg",
                        ownerDescription = "Passionate about creating delicious and easy-to-follow recipes for everyone.",
                    ),
                    CompleteMealContainer(
                        completeMealModelList = listOf(
                            CompleteMealModel(
                                id = 1.1,
                                name = "Garlic Bread",
                                image = "https://example.com/garlic_bread.jpg",
                                ratingPoint = 4.2f, ingredientAmt = 5,

                            ),
                            CompleteMealModel(
                                id = 1.2,
                                name = "Caesar Salad",
                                image = "https://example.com/caesar_salad.jpg",
                                ratingPoint = 4.0f,
                                ingredientAmt = 7
                            ),
                            CompleteMealModel(
                                id = 1.3,
                                name = "Roasted Vegetables",
                                image = "https://example.com/roasted_veggies.jpg",
                                ratingPoint = 4.5f,
                                ingredientAmt = 6
                            ),
                            CompleteMealModel(
                                id = 1.4,
                                name = "Creamy Tomato Soup",
                                image = "https://example.com/tomato_soup.jpg",
                                ratingPoint = 4.7f,
                                ingredientAmt = 8
                            ),
                            CompleteMealModel(
                                id = 1.5,
                                name = "Fruit Salad",
                                image = "https://example.com/fruit_salad.jpg",
                                ratingPoint = 4.3f,
                                ingredientAmt = 4
                            )
                        )
                    ),
                    AlsoLikeContainer(
                        alsoLikeModelList = listOf(
                            AlsoLikeModel(
                                id = 2.1,
                                name = "Chocolate Lava Cake",
                                image = "https://example.com/lava_cake.jpg",
                                ingredientAmt = 8
                            ),
                            AlsoLikeModel(
                                id = 2.2,
                                name = "Strawberry Shortcake",
                                image = "https://example.com/shortcake.jpg",
                                ingredientAmt = 10
                            ),
                            AlsoLikeModel(
                                id = 2.3,
                                name = "Blueberry Muffins",
                                image = "https://example.com/muffins.jpg",
                                ingredientAmt = 6
                            ),
                            AlsoLikeModel(
                                id = 2.4,
                                name = "Apple Pie",
                                image = "https://example.com/apple_pie.jpg",
                                ingredientAmt = 12
                            ),
                            AlsoLikeModel(
                                id = 2.5,
                                name = "Cheesecake",
                                image = "https://example.com/cheesecake.jpg",
                                ingredientAmt = 10
                            )
                        )
                    )
                )
            )
        )
    )
}

