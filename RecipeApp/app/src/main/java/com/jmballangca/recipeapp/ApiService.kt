package com.jmballangca.recipeapp

import com.jmballangca.recipeapp.models.RecipeResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


private val _api = Retrofit
    .Builder()
    .baseUrl("https://jellybellywikiapi.onrender.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = _api.create(ApiService::class.java)
interface ApiService {
    @GET("Recipes")
    suspend fun getRecipe(
        @Query("pageIndex") pageIndex : Int,
        @Query("pageSize") pageSize : Int = 10
    ) : RecipeResponse
}