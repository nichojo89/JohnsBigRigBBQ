package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nicholssoftware.johnsbigrigbbq.R
import com.nicholssoftware.johnsbigrigbbq.ui.framework.ServiceLocator
import com.nicholssoftware.johnsbigrigbbq.ui.presentation.ui.theme.JohnsBigRigBBQTheme
import com.nicholssoftware.johnsbigrigbbq.ui.theme.Red
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nicholssoftware.core.data.Dish


@Composable
fun DishDetailsScreen(navController: NavController, mainViewModel: MainViewModel,viewModel: DishDetailsViewModel = viewModel()){
    val dish by viewModel.dish.collectAsState()
    LaunchedEffect(Unit){
        mainViewModel.setTitle(NavigationItem.DishDetails.title)
    }

    JohnsBigRigBBQTheme {
        Column(Modifier.fillMaxSize()){
            DishHeader(dish)
            DishDescription(dish)
            Button(onClick = {
                //Add to cart
                ServiceLocator.cart.add(dish)
                //TODO mainViewModel.setTitle(NavigationItem.Checkout.title)
                navController.navigate(NavigationItem.Checkout.route){
                    navController.graph.startDestinationRoute?.let { route ->
                        popUpTo(route){
                            saveState = true
                        }
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            },

            modifier = Modifier
                .padding(15.dp)
                .align(Alignment.CenterHorizontally)
                , colors = ButtonDefaults.buttonColors(
                    Red, Color.White
                )
                , shape = RoundedCornerShape(23.dp)
                ) {
               Text(text = "Add to cart")
            }
        }
    }
}

@Composable
fun DishHeader(dish: Dish){
    Column(modifier = Modifier
        .padding(20.dp)
        .wrapContentHeight()){
        Text(text = dish.title,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)

        Text(dish.type,
            fontSize = 14.sp)
        Divider(color = Color.Gray,thickness = 2.dp)
    }
}

@Composable
fun DishDescription(dish: Dish){
    val painter: Painter = painterResource(dish.imageResource)
    Column{
        Image(
            painter = painter,
            contentDescription = dish.description,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(80.dp, 10.dp, 80.dp, 10.dp)
        )
        Text(modifier = Modifier.padding(50.dp,0.dp,50.dp,0.dp),
            text = dish.description
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DishDescriptionPreview(){
    JohnsBigRigBBQTheme {
        Column{
//            DishHeader(Dish())
//            DishDescription()
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    JohnsBigRigBBQTheme {
//        DishDetailsScreen()
//    }
//}