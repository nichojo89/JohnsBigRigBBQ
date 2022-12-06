package com.nicholssoftware.johnsbigrigbbq.ui.framework

import com.nicholssoftware.core.data.Dish
import java.text.NumberFormat
import java.util.*
import kotlin.collections.HashMap

object ServiceLocator {
    private var _cart = HashMap<Dish, Int>()
    val cart get() = _cart
    private val cf = NumberFormat.getCurrencyInstance(Locale("en","US"))

    fun AddToCart(dish: Dish){
        if(_cart.contains(dish))
            _cart[dish] = _cart[dish]!!.plus(1)
        else
            _cart[dish] = 1
    }

    fun RemoveFromCart(order: Pair<Dish,Int>) {
        if(_cart.containsKey(order.first))
            _cart.remove(order.first)
    }

    fun UpdateCart(order: Pair<Dish,Int>){
        if(_cart.containsKey(order.first))
            if(order.second >= 0)
                _cart[order.first] = order.second
    }

    fun GetTotal(tip: Double): String = cf.format(
        cart.toList()
            .sumOf { it.first.price * it.second } + tip)

    fun GetTax(): String =  cf.format(cart.toList().sumOf { it.first.price * it.second } * 0.06)

    fun GetItemTotal(order: Pair<Dish,Int>) = cf.format(order.first.price * order.second)
}