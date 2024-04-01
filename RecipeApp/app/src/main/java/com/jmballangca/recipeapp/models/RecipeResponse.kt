package com.jmballangca.recipeapp.models

data class RecipeResponse(
    val totalCount: Int,
    val pageSize: Int,
    val currentPage: Int,
    val totalPages: Int,
    val items : List<Recipe>
)


