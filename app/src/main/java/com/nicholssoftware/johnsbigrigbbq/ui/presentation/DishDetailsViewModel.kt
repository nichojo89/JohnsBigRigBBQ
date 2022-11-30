package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nicholssoftware.core.data.Dish
import com.nicholssoftware.johnsbigrigbbq.ui.repository.DishRepository
import kotlinx.coroutines.flow.*

data class DiceUiState(
    val firstDieValue: Int? = null,
    val secondDieValue: Int? = null,
    val numberOfRolls: Int = 0,
)

class DishDetailsViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val dishRepository: DishRepository = DishRepository()
    private val dishId: String = checkNotNull(savedStateHandle["dish_id"])

    private var _dish = MutableStateFlow(Dish())
    val dish: StateFlow<Dish> = _dish.asStateFlow()

    init {
        val d = dishRepository.getDishById(dishId.toLong())
        _dish.update { d }
    }
}