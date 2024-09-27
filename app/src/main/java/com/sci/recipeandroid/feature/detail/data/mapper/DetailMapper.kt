package com.sci.recipeandroid.feature.detail.data.mapper

import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeContainer
import com.sci.recipeandroid.feature.detail.domain.model.AlsoLikeModel
import com.sci.recipeandroid.feature.detail.domain.model.AmountPerMetricUnit
import com.sci.recipeandroid.feature.detail.domain.model.AmountPerUsUnit
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealContainer
import com.sci.recipeandroid.feature.detail.domain.model.CompleteMealModel
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
                        name = "Chick",
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 1.5,
                            metricUnit = "g"
                        ),
                        amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 1.0,
                            usUnit = "pound"
                        ),
                        increaseRate = 1.5,
                        image = "flour_image_url",
                    ),
                    IngredientsModel(
                        name = "Sugar",
                        increaseRate = 2.0,
                        image = "sugar_image_url",
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 1.0,
                            metricUnit = "g"
                        ), amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 2.0,
                            usUnit = "tsp"
                        )
                    ),
                    IngredientsModel(
                        name = "Flour",
                        increaseRate = 1.5,
                        image = "flour_image_url",
                        amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 3.0,
                            usUnit = "tsp"
                        ),
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 1.5,
                            metricUnit = "g"
                        ),
                    ),
                    IngredientsModel(
                        name = "Sugar",
                        increaseRate = 2.0,
                        image = "sugar_image_url",
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 2.0,
                            metricUnit = "g"
                        ),
                        amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 4.0,
                            usUnit = "pound"
                        )
                    ),
                    IngredientsModel(
                        name = "Eggs",
                        increaseRate = 1.0,
                        image = "egg_image_url",
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 2.5,
                            metricUnit = "g"
                        ),
                        amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 5.0,
                            usUnit = "pound"
                        )
                    ),
                    IngredientsModel(
                        name = "Butter",
                        increaseRate = 1.25,
                        image = "butter_image_url",
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 3.0,
                            metricUnit = "g"
                        ),
                        amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 6.0,
                            usUnit = "pound"
                        )
                    ),
                    IngredientsModel(
                        name = "Milk",
                        increaseRate = 2.0,
                        image = "milk_image_url",
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 4.0,
                            metricUnit = "g"
                        ),
                        amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 7.0,
                            usUnit = "pound"
                        )
                    ),
                    IngredientsModel(
                        name = "Baking Powder",
                        increaseRate = 1.0,
                        image = "baking_powder_image_url",
                        amountPerMetricUnit = AmountPerMetricUnit(
                            metricAmt = 5.0,
                            metricUnit = "g"
                        ),
                        amountPerUsUnit = AmountPerUsUnit(
                            usAmt = 8.0,
                            usUnit = "pound"
                        )
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

