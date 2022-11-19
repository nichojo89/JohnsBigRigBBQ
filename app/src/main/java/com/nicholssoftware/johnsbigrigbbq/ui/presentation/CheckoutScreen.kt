package com.nicholssoftware.johnsbigrigbbq.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicholssoftware.core.data.Dish
import com.nicholssoftware.johnsbigrigbbq.ui.repository.DishRepository
import com.nicholssoftware.johnsbigrigbbq.ui.theme.JohnsBigRigBBQTheme
import com.nicholssoftware.johnsbigrigbbq.ui.theme.Red
import java.text.NumberFormat
import java.util.*

@Preview(showBackground = true)
@Composable
fun CheckoutScreen(){
    JohnsBigRigBBQTheme {
        Surface {
            OrderItems()
        }
    }
}

@Composable
fun OrderItems(){
    //TODO This should be injected using dependancy injection
    Column(modifier = Modifier.fillMaxSize()){
        val list = DishRepository().getAllDishes().take(3)
        LazyColumn(modifier = Modifier.fillMaxHeight()
            .align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(16.dp)
        ){
            items(list) { item ->
                OrderCard(dish = item)

            }
            item {
                OrderDetails(list)
            }
        }

    }
}

@Composable
fun OrderCard(dish: Dish){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,

    ){
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement  =  Arrangement.Start,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            Image(
                painter = painterResource(id = dish.imageResource),
                contentDescription = dish.contentDescription,
                modifier = Modifier
                    .size(60.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(6.dp)),
                contentScale = ContentScale.Fit,
            )

            Text(dish.title)
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement  =  Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 5.dp, 20.dp, 5.dp))
        {
            var itemCount by remember { mutableStateOf(TextFieldValue("")) }
            itemCount = TextFieldValue("1")
            TextField(
                value = itemCount,
                onValueChange = { newText ->
                    itemCount = newText
                },
                modifier = Modifier.width(40.dp),
                keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Number)
            )

            val currencyFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            val price = currencyFormat.format(dish.price)
            Text(price, modifier = Modifier.padding(20.dp, 0.dp,20.dp, 0.dp))
        }
    }
}

@Composable
fun OrderDetails(list: List<Dish>){
    val tax = list.sumOf{it.price} * 0.06
    Row(horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)){
        Text(text = "Tax", fontWeight = FontWeight.Bold)

        val cf = NumberFormat.getCurrencyInstance(Locale("en","US"))
        val t = cf.format(tax)
        Text(text = t)
    }

    var tip by remember { mutableStateOf(TextFieldValue("")) }
    tip = TextFieldValue("1")

    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)){
        Text(text = "Add tip", fontWeight = FontWeight.Bold)



        TextField(
            value = tip,
            onValueChange = { newText ->
                tip = newText
            },
            modifier = Modifier.width(40.dp),
            keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Number)
        )


    }
    Divider(color = Color.Gray, thickness = 2.dp)
    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)){
        Text(text = "Total", fontWeight = FontWeight.Bold)

        val s = tax+tip.text.toInt()+list.sumOf{it.price}
        val cf = NumberFormat.getCurrencyInstance(Locale("en","US"))
        val sm = cf.format(s)
        Text(text = sm)
    }
    Button(onClick = {
        /*TODO navigate to checkout*/
    },

        modifier = Modifier
            .padding(15.dp)
        , colors = ButtonDefaults.buttonColors(
            Red, Color.White
        )
        , shape = RoundedCornerShape(23.dp)
    ) {
        Text(text = "Add to cart")
    }

}
