package com.jmballangca.recipeapp

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jmballangca.recipeapp.models.Recipe
import kotlinx.coroutines.launch

const val TAG = "recipes"

class RecipeViewModel : ViewModel() {
    private val apiService = api
    private val _recipeState = mutableStateOf(RecipeState())
    val recipeState : State<RecipeState> = _recipeState
    init {
        viewModelScope.launch {
            try {
              val  result = apiService.getRecipe(1)
               _recipeState.value =  _recipeState.value.copy(
                    recipes = result.items,
                   loading = false
                )
                Log.d(TAG,"RECIPES : ${_recipeState.value.recipes}")
                Log.d(TAG,result.toString())

            } catch (e : Exception) {
               _recipeState.value =  _recipeState.value.copy(
                    error = e.message.toString(),
                   loading = false
                )
                Log.d(TAG,e.message.toString())
            } finally {
                _recipeState.value.copy(
                    loading = false
                )
            }

        }
    }


    data class RecipeState(
        var loading : Boolean = true,
        var recipes : List<Recipe> = emptyList(),
        var error : String ? = null
    )
}