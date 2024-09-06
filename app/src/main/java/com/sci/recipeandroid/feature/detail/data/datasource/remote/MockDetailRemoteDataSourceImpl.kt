package com.sci.recipeandroid.feature.detail.data.datasource.remote

import com.sci.recipeandroid.feature.detail.data.mapper.toNutritionScreenData
import com.sci.recipeandroid.feature.detail.data.model.Nutrition
import com.sci.recipeandroid.feature.detail.data.model.NutritionResponse
import com.sci.recipeandroid.feature.detail.data.model.NutritionSubType

class MockDetailRemoteDataSourceImpl() : DetailRemoteDataSource {
    override fun getDetail(id: Double): Result<String> {
        return Result.success("")
    }

    override fun getNutritionPerServing(id: Double): Result<NutritionResponse> {
        return Result.success(
            NutritionResponse(
                calories = "259",
                nutritionTitle = listOf(
                    // region data 1
                    Nutrition(
                        id = 1.0,
                        typeName = "Carbohydrates",
                        dailyValue = "300 g",
                        amount = "150 g",
                        subType = listOf(
                            NutritionSubType(
                                id = 4.1,
                                typeName = "Sugar",
                                subAmount = "30 g",
                                dailyValue = "10%"
                            ),
                            NutritionSubType(
                                id = 4.2,
                                typeName = "Fiber",
                                subAmount = "15 g",
                                dailyValue = "50%"
                            )
                        )
                    ),
                    //endregion
                    //region data 2
                    Nutrition(
                        id = 2.0,
                        typeName = "Protein",
                        dailyValue = "50 g", amount = "20 g",
                        subType = listOf(
                            NutritionSubType(
                                id = 2.1,
                                typeName = "Animal Protein",
                                subAmount = "15 g",
                                dailyValue = "75%"
                            )
                        )
                    ),
                    //endregion
                    //region data 3
                    Nutrition(
                        id = 3.0,
                        typeName = "Fat",
                        dailyValue = "65 g",
                        amount = "15 g",
                        subType = listOf(
                            NutritionSubType(
                                id = 3.1,
                                typeName = "Saturated Fat",
                                subAmount = "5 g",
                                dailyValue = "25%"
                            ),
                            NutritionSubType(
                                id = 3.2,
                                typeName = "Unsaturated Fat",
                                subAmount = "10 g",
                                dailyValue = "50%"
                            )
                        )
                    ),
                    //endregion
                    //region data 4
                    Nutrition(
                        id = 4.0,
                        typeName = "Carbohydrates", dailyValue = "300 g",
                        amount = "150 g",
                        subType = listOf(
                            NutritionSubType(
                                id = 4.1,
                                typeName = "Sugar",
                                subAmount = "30 g",
                                dailyValue = "10%"
                            ),
                            NutritionSubType(
                                id = 4.2,
                                typeName = "Fiber",
                                subAmount = "15 g",
                                dailyValue = "50%"
                            )
                        )
                    ),
                    //endregion
                    //region data 5
                    Nutrition(
                        id = 5.0,
                        typeName = "Vitamin C",
                        dailyValue = "90 mg",
                        amount = "60 mg",
                        subType = listOf(
                            NutritionSubType(
                                id = 5.1,
                                typeName = "From Fruits",
                                subAmount = "40 mg",
                                dailyValue = "44%"
                            ), NutritionSubType(
                                id = 5.2,
                                typeName = "From Vegetables",
                                subAmount = "20 mg",
                                dailyValue = "22%"
                            )
                        )
                    ),
                    //endregion
                    //region data 6
                    Nutrition(
                        id = 6.0,
                        typeName = "Fiber",
                        dailyValue = "25 g",
                        amount = "10 g",
                        subType = listOf(
                            NutritionSubType(
                                id = 6.1,
                                typeName = "Soluble Fiber",
                                subAmount = "4 g",
                                dailyValue = "16%"
                            ), NutritionSubType(
                                id = 6.2,
                                typeName = "Insoluble Fiber",
                                subAmount = "6 g",
                                dailyValue = "24%"
                            )
                        )
                    ),
                    //endregion
                    //region data 7
                    Nutrition(
                        id = 7.0,
                        typeName = "Iron",
                        dailyValue = "18 mg",
                        amount = "8 mg",
                        subType = listOf(
                            NutritionSubType(
                                id = 7.1, typeName = "Heme Iron",
                                subAmount = "3 mg",
                                dailyValue = "17%"
                            ),
                            NutritionSubType(
                                id = 7.2,
                                typeName = "Non-Heme Iron",
                                subAmount = "5 mg",
                                dailyValue = "28%"
                            )
                        )
                    ),
                    //endregion
                    //region data 8
                    Nutrition(
                        id = 8.0,
                        typeName = "Calcium",
                        dailyValue = "1000 mg",
                        amount = "300 mg",
                        subType = listOf(
                            NutritionSubType(
                                id = 8.1,
                                typeName = "From Dairy",
                                subAmount = "200 mg",
                                dailyValue = "20%"
                            ),
                            NutritionSubType(
                                id = 8.2,
                                typeName = "From Non-Dairy",
                                subAmount = "100 mg",
                                dailyValue = "10%"
                            )
                        )
                    ),
                    //endregion
                    //region data 9
                    Nutrition(
                        id = 9.0,
                        typeName = "Potassium",
                        dailyValue = "4700 mg",
                        amount = "1200 mg",
                        subType = emptyList()
                    ),
                    //endregion
                    //region data 10
                    Nutrition(
                        id = 10.0,
                        typeName = "Sodium",
                        dailyValue = "2300 mg",
                        amount = "600 mg",
                        subType = emptyList()
                    )
                    //endregion
                ),
                notice = "* Percent Daily Values are based on a 2,000 calorie diet. Your Daily values may be higher or lower depending on your calorie needs.",
                disClaimer = "Disclaimer: All information and tools presented and written within this site are intended for informational purposes only. If you have any concerns or questions about your health, please consult with a physician or other health-care professional."
            )
        )

    }

    override fun getDirection(id: Double): Result<String> {return Result.success("")}

}