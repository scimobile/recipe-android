package com.sci.recipeandroid.feature.personalize.domain.service


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
                            id = 0,
                            imageUrl = "https://example.com/images/none.jpg",
                            name = "None"
                        ),
                        DietRecipe(
                            id = 1,
                            imageUrl = "https://example.com/images/vegan.jpg",
                            name = "Vegan"
                        ),
                        DietRecipe(
                            id = 2,
                            imageUrl = "https://example.com/images/vegetarian.jpg",
                            name = "Vegetarian"
                        ),
                        DietRecipe(
                            id = 3,
                            imageUrl = "https://example.com/images/pescatarian.jpg",
                            name = "Pescatarian"
                        ),
                        DietRecipe(
                            id = 4,
                            imageUrl = "https://example.com/images/paleo.jpg",
                            name = "Paleo"
                        ),
                        DietRecipe(
                            id = 5,
                            imageUrl = "https://example.com/images/low_carb.jpg",
                            name = "Low-Carb"
                        ),
                        DietRecipe(
                            id = 6,
                            imageUrl = "https://example.com/images/keto.jpg",
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
                            id = 1,
                            iconUrl = "https://picsum.photos/200",
                            name = "None"
                        ),
                        PersonalizeGoals(
                            id = 2,
                            iconUrl = "https://example.com/images/none.jpg",
                            name = "None"
                        ),
                        PersonalizeGoals(
                            id = 3,
                            iconUrl = "https://example.com/images/none.jpg",
                            name = "None"
                        ),
                        PersonalizeGoals(
                            id = 4,
                            iconUrl = "https://example.com/images/none.jpg",
                            name = "None"
                        ),
                        PersonalizeGoals(
                            id = 5,
                            iconUrl = "https://example.com/images/none.jpg",
                            name = "None"
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
