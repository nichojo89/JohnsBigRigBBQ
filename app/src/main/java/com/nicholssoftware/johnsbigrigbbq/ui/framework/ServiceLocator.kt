package com.nicholssoftware.johnsbigrigbbq.ui.framework

import com.nicholssoftware.core.data.Dish

object ServiceLocator {
    private var _cart = HashMap<Dish, Int>()
    val cart get() = _cart

    fun AddToCart(dish: Dish){
        if(_cart.contains(dish))
            _cart[dish] = _cart[dish]!!.plus(1)
        else
            _cart[dish] = 1
    }

    fun UpdateCart(dish: Dish){
        if(_cart.containsKey(dish)){
            if(_cart[dish] == 1)
                _cart.remove(dish)
            else
                _cart[dish] = _cart[dish]!!.plus(1)
        }
    }
}