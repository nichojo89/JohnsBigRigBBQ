package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nicholssoftware.johnsbigrigbbq.ui.theme.JohnsBigRigBBQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JohnsBigRigBBQTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){
    title = NavigationItem.Menu.title
    val navController = rememberNavController()
    Scaffold(
        topBar = { TopBar()},
        bottomBar = { BottomNavigationBar(navController)},
        content = { p ->
                  Box(modifier = Modifier.padding(p)){
                      Navigation(navController = navController)
                  }
        },
        backgroundColor = MaterialTheme.colors.background
    )
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController, startDestination = NavigationItem.Menu.route) {
        composable(NavigationItem.Menu.route) {
            MenuScreen()
        }
        composable(NavigationItem.Checkout.route){
            CheckoutScreen()
        }
        composable(NavigationItem.Truck.route){
            TruckScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JohnsBigRigBBQTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun TopBar(){
    TopAppBar(
        title = {Text(title)},
        contentColor = Color.White
    )
}

//@Preview(showBackground = true)
@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        NavigationItem.Menu,
        NavigationItem.Checkout,
        NavigationItem.Truck
    )
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach{ item ->
            BottomNavigationItem(
                icon = { Icon(
                    painterResource( id = item.icon),
                    contentDescription = item.title,
                    modifier = Modifier.height(20.dp)
                )},
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    title = item.title
                    navController.navigate(item.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

private var title by mutableStateOf("")

