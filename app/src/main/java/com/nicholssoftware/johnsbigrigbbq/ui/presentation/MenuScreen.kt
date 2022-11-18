package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nicholssoftware.johnsbigrigbbq.ui.presentation.NavigationItem.Checkout.title
import com.nicholssoftware.johnsbigrigbbq.ui.repository.DishRepository
import com.nicholssoftware.johnsbigrigbbq.ui.theme.color

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun MenuScreen(navController: NavController, mainViewModel: MainViewModel){
    LaunchedEffect(Unit){
        mainViewModel.setTitle(NavigationItem.Menu.title)
    }

    Column {
        //TODO Use dependancy injection
        val dishRepo = DishRepository()
        val dishes = dishRepo.getAllDishes()
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            // content padding
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(dishes.size) { index ->
                    Card(
                        backgroundColor = "#f5f5f5".color,
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth(),
                        elevation = 8.dp,
                        onClick = {
                            val route = NavigationItem.DishDetails.route
                            title = "test"
                            navController.navigate(route)
                        }
                    ) {
                        Column{
                            val painter: Painter = painterResource(id = dishes[index].imageResource)
                            Image(painter = painter,
                                contentDescription = dishes[index].contentDescription,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Text(
                                text = dishes[index].title,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,

                                modifier = Modifier
                                    .padding(16.dp)
                                    .align(CenterHorizontally)
                            )
                        }
                    }
                }
            }
        )
    }
}