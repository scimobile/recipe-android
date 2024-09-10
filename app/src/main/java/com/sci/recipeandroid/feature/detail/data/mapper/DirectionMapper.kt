package com.sci.recipeandroid.feature.detail.data.mapper

import com.sci.recipeandroid.feature.detail.domain.model.DirectionModel

fun String.toDirectionModel(): List<DirectionModel> {
    val tempList = mutableListOf<DirectionModel>()
    for (i in 1..10) {
        tempList.add(
            DirectionModel(
                id = i.toDouble(),
                name = "Step $i",
                image = when (i) {
                    in 1..9 -> "https://media.cnn.com/api/v1/images/stellar/prod/211006114703-best-meal-delivery-service-freshly.jpg?q=w_1601,h_900,x_0,y_0,c_fill"
                    else -> ""
                },
                description =
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. "
            )

        )
    }
    return tempList
}