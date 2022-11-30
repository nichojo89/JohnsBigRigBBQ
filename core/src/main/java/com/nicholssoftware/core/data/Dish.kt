package com.nicholssoftware.core.data

data class Dish(
    var title: String = "",
    var description: String = "",
    var contentDescription: String = "",
    var imageResource: Int = 0,
    var price: Double = 0.0,
    var id: Long = 0L)