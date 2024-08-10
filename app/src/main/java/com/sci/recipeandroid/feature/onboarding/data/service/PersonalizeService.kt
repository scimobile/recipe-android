package com.sci.recipeandroid.feature.onboarding.data.service


import com.sci.recipeandroid.feature.onboarding.data.model.response.AllergiesIngredient
import com.sci.recipeandroid.feature.onboarding.data.model.response.DietRecipe
import com.sci.recipeandroid.feature.onboarding.data.model.response.PersonalizeData
import com.sci.recipeandroid.feature.onboarding.data.model.response.PersonalizeResponse
import io.ktor.client.HttpClient

class PersonalizeService(private val httpClient: HttpClient) {
    suspend fun getPersonalize(): PersonalizeResponse {
        return PersonalizeResponse(
            code = 200,
            message = "Success",
            data = PersonalizeData(
                dietRecipe = listOf(
                    DietRecipe(
                        id = "none",
                        imageUrl = "https://example.com/images/none.jpg",
                        name = "None"
                    ),
                    DietRecipe(
                        id = "vegan",
                        imageUrl = "https://example.com/images/vegan.jpg",
                        name = "Vegan"
                    ),
                    DietRecipe(
                        id = "vegetarian",
                        imageUrl = "https://example.com/images/vegetarian.jpg",
                        name = "Vegetarian"
                    ),
                    DietRecipe(
                        id = "pescatarian",
                        imageUrl = "https://example.com/images/pescatarian.jpg",
                        name = "Pescatarian"
                    ),
                    DietRecipe(
                        id = "paleo",
                        imageUrl = "https://example.com/images/paleo.jpg",
                        name = "Paleo"
                    ),
                    DietRecipe(
                        id = "low_carb",
                        imageUrl = "https://example.com/images/low_carb.jpg",
                        name = "Low-Carb"
                    ),
                    DietRecipe(
                        id = "keto",
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
                )
            )
        )
        /*val response: PersonalizeResponse = withContext(Dispatchers.IO) {
            val httpResponse : HttpResponse = httpClient.get("") //get is suspend fun
            //meta data,body
            httpResponse.body() //body json //body is suspend fun
        }
        return response*/
    }
}
