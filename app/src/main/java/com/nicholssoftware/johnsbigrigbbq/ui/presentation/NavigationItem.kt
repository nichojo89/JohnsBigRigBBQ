package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import com.nicholssoftware.johnsbigrigbbq.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String){
    object Menu: NavigationItem("menu", R.drawable.ic_grill, "Menu")
    object Checkout: NavigationItem("checkout", R.drawable.ic_grill, "Checkout")
    object Truck: NavigationItem("truck", R.drawable.ic_grill, "Truck")
    object DishDetails: NavigationItem("dish_details/{dish_id}", R.drawable.ic_grill, "Dish Details")
}