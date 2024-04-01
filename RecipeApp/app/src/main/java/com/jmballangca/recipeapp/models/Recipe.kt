package com.jmballangca.recipeapp.models

data class Recipe(
    val recipeId: Int,
    val name: String,
    val description: String,
    val prepTime: String,
    val cookTime: String,
    val totalTime: String,
    val makingAmount: String,
    val imageUrl: String,
    val ingredients: List<String>,
    val additions1: List<String>,
    val additions2: List<String>,
    val additions3: List<String>,
    val directions: List<String>,
    val tips: List<String>
)
