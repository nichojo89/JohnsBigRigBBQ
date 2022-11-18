package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
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
import com.nicholssoftware.johnsbigrigbbq.R
import com.nicholssoftware.johnsbigrigbbq.ui.presentation.ui.theme.JohnsBigRigBBQTheme
import com.nicholssoftware.johnsbigrigbbq.ui.theme.Red

@Composable
fun DishDetailsScreen(){
    JohnsBigRigBBQTheme {
        Column(Modifier.fillMaxSize()){
            DishHeader()
            DishDescription()
            Button(onClick = {
            /*TODO navigate to checkout*/
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
fun DishHeader(){
    Column(modifier = Modifier.padding(20.dp)
        .wrapContentHeight()){
        Text(text = "Signature Brisket",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp)

        Text("Pork",
            fontSize = 14.sp)
        Divider(color = Color.Gray,thickness = 2.dp)
    }
}

@Composable
fun DishDescription(){
    val painter: Painter = painterResource(R.drawable.ic_brisket)
    Column{
        Image(
            painter = painter,
            contentDescription = "Yummy Brisket",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .padding(80.dp,10.dp,80.dp,10.dp)
        )
        Text(modifier = Modifier.padding(50.dp,0.dp,50.dp,0.dp),
            text = "The pit boss. Made with Angus beef. Made " +
                    "with whole premium cuts. Brisket beef patties " +
                    "with bacon and barbecue rub. Try the Brisket " +
                    "Beef Patty with red curly leaf lettuce, sliced " +
                    "tomatoes, smoked cheddar cheese, tomato jam and bacon."
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DishDescriptionPreview(){
    JohnsBigRigBBQTheme {
        Column{
            DishHeader()
            DishDescription()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JohnsBigRigBBQTheme {
        DishDetailsScreen()
    }
}