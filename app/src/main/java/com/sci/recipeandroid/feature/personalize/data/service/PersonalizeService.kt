package com.sci.recipeandroid.feature.personalize.data.service


import com.sci.recipeandroid.feature.personalize.data.mapper.mapIconResNameToId
import com.sci.recipeandroid.feature.personalize.data.model.response.AllergiesIngredient
import com.sci.recipeandroid.feature.personalize.data.model.response.DietRecipe
import com.sci.recipeandroid.feature.personalize.data.model.response.PersonalizeData
import com.sci.recipeandroid.feature.personalize.data.model.response.PersonalizeGoals
import com.sci.recipeandroid.feature.personalize.data.model.response.PersonalizeResponse
import io.ktor.client.HttpClient

class PersonalizeService(private val httpClient: HttpClient) {
    suspend fun getPersonalize(): Result<PersonalizeResponse> {

        return try {
            val response = PersonalizeResponse(
                code = 200,
                message = "Success",
                data = PersonalizeData(
                    dietRecipe = listOf(
                        DietRecipe(
                            id = "0",
                            imageUrl = mapIconResNameToId("none_image"),
                            name = "None"
                        ),
                        DietRecipe(
                            id = "1",
                            imageUrl = mapIconResNameToId("vegan_image"),
                            name = "Vegan"
                        ),
                        DietRecipe(
                            id = "2",
                            imageUrl = mapIconResNameToId("vegetarian_image"),
                            name = "Vegetarian"
                        ),
                        DietRecipe(
                            id = "3",
                            imageUrl = mapIconResNameToId("pescatarian_image"),
                            name = "Pescatarian"
                        ),
                        DietRecipe(
                            id = "4",
                            imageUrl = mapIconResNameToId("paleo_image"),
                            name = "Paleo"
                        ),
                        DietRecipe(
                            id = "5",
                            imageUrl = mapIconResNameToId("low_crab_image"),
                            name = "Low-Carb"
                        ),
                        DietRecipe(
                            id = "6",
                            imageUrl = mapIconResNameToId("keto_image"),
                            name = "Keto"
                        )
                    ),
                    allergiesIngredient = listOf(
                        AllergiesIngredient(
                            id = "gluten",
                            name = "Gluten"
                        ),
                        AllergiesIngredient(
                            id = "dairy",
                            name = "Dairy"
                        ),
                        AllergiesIngredient(
                            id = "egg",
                            name = "Egg"
                        ),
                        AllergiesIngredient(
                            id = "soy",
                            name = "Soy"
                        ),
                        AllergiesIngredient(
                            id = "peanut",
                            name = "Peanut"
                        ),
                        AllergiesIngredient(
                            id = "tree_nut",
                            name = "Tree Nut"
                        ),
                        AllergiesIngredient(
                            id = "fish",
                            name = "Fish"
                        ),
                        AllergiesIngredient(
                            id = "shellfish",
                            name = "Shellfish"
                        )
                    ),
                    personalizeGoals = listOf(
                        PersonalizeGoals(
                            id = "1",
                            name = "Eat Healthy",
                            iconUrl = mapIconResNameToId("eat_healthy_icon")
                        ),
                        PersonalizeGoals(
                            id = "2",
                            name = "Budget Friendly",
                            iconUrl = mapIconResNameToId("budget_friendly_icon")
                        ),
                        PersonalizeGoals(
                            id = "3",
                            name = "Plan Better",
                            iconUrl = mapIconResNameToId("plan_better_icon")
                        ),
                        PersonalizeGoals(
                            id = "4",
                            name = "Learn to Cook",
                            iconUrl = mapIconResNameToId("learn_to_cook_icon")
                        ),
                        PersonalizeGoals(
                            id = "5",
                            name = "Quick & Easy",
                            iconUrl = mapIconResNameToId("quick_and_easy_icon")
                        )
                    )
                )
            )
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }


        /*val response: PersonalizeResponse = withContext(Dispatchers.IO) {
            val httpResponse : HttpResponse = httpClient.get("") //get is suspend fun
            //meta data,body
            httpResponse.body() //body json //body is suspend fun
        }
        return response*/
    }
}
