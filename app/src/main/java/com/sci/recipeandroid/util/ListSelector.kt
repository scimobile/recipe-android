package com.sci.recipeandroid.util

fun <T> List<T>.multiSelectBy(
    selectedId: Double,
    selector: (T) -> Pair<Double, Boolean>,
    bind: (T, Boolean) -> T
) = this.map {
    val (id, isSelect) = selector(it)
    if (selectedId == id)
        bind(it, !isSelect)
    else bind(it, isSelect)
}