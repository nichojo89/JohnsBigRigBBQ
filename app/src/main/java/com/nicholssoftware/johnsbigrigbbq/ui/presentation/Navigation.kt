package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Navigation(navController: NavHostController,
               mainViewModel: MainViewModel){
    NavHost(navController, startDestination = NavigationItem.Menu.route) {
        composable(NavigationItem.Menu.route) {
            MenuScreen(navController,mainViewModel)
        }
        composable(NavigationItem.Checkout.route){
            CheckoutScreen(navController)
        }
        composable(NavigationItem.Truck.route){
            TruckScreen()
        }
        composable(NavigationItem.DishDetails.route,
            arguments = listOf(navArgument("dish_id"){type = NavType.StringType})
        ){
            DishDetailsScreen(navController, mainViewModel)
        }
    }
}