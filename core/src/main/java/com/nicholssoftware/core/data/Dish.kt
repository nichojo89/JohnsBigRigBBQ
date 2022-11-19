package com.nicholssoftware.core.data

data class Dish(
    var title: String,
    var description: String,
    var contentDescription: String,
    var imageResource: Int,
    var price: Double,
    var id: Long = 0L)