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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nicholssoftware.core.data.Dish
import com.nicholssoftware.johnsbigrigbbq.ui.framework.ServiceLocator
import com.nicholssoftware.johnsbigrigbbq.ui.theme.JohnsBigRigBBQTheme
import com.nicholssoftware.johnsbigrigbbq.ui.theme.Red
import java.text.NumberFormat
import java.util.*

@Composable
fun CheckoutScreen(navController: NavController){
    JohnsBigRigBBQTheme {
        Surface {
            OrderItems(navController) {navController.navigate(NavigationItem.Truck.route)}
        }
    }
}

@Composable
fun OrderItems(navController: NavController, onNavigateToTruck: () -> Unit){
    Column(modifier = Modifier.fillMaxSize()){
        val list = ServiceLocator.cart.toList()
        //TODO cleanup coce by vs = ???
        val grandTotal = remember {mutableStateOf("")}
        var tip by remember { mutableStateOf(TextFieldValue("0")) }

        LazyColumn(modifier = Modifier.fillMaxHeight()
            .align(Alignment.CenterHorizontally),
            contentPadding = PaddingValues(16.dp)
        ){
            items(list) { item ->
                OrderCard(order = item, grandTotal, tip)

            }
            item {
                OrderDetails(order = list, grandTotal, tip) {onNavigateToTruck()}
            }
        }

    }
}

@Composable
fun OrderCard(order : Pair<Dish, Int>, bill: MutableState<String>, tip: TextFieldValue){
    val dish = order.first
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
            val qty = order.second
            var itemCount by remember { mutableStateOf(TextFieldValue("$qty")) }

            TextField(

                value = itemCount,
                modifier = Modifier.width(60.dp),
                onValueChange = {
                        newText -> itemCount = newText
                    //Recalculate on qty change
                    val cf = NumberFormat.getCurrencyInstance(Locale("en","US"))
                    //TODO
                    val s = tax+tip.text.toInt()+total
                    bill.value = cf.format(s)
                },
                keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Number)
            )

            val currencyFormat = NumberFormat.getCurrencyInstance(Locale("en", "US"))
            val price = currencyFormat.format(dish.price)
            Text(price, modifier = Modifier.padding(20.dp, 0.dp,20.dp, 0.dp))
        }
    }
}

@Composable
fun OrderDetails(order: List<Pair<Dish, Int>>, grandTotal: MutableState<String>, tip: TextFieldValue, onNavigateToTruck: () -> Unit){
    val total = order.sumOf { it.first.price * it.second }
    val tax = total * 0.06
    val cf = NumberFormat.getCurrencyInstance(Locale("en","US"))

    Row(horizontalArrangement = Arrangement.SpaceBetween,
    modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)){
        Text(text = "Tax", fontWeight = FontWeight.Bold)

        val cf = NumberFormat.getCurrencyInstance(Locale("en","US"))
        val t = cf.format(tax)

        Text(text = t)
    }



    Row(horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)){
        Text(text = "Add tip", fontWeight = FontWeight.Bold)



        TextField(
            value = tip,
            onValueChange = { newText ->
                tip = newText
                //Recalculate price after adjusting tip
                val s = tax+tip.text.toInt()+total
                grandTotal.value = cf.format(s)
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

        //Set initial bill
        val s = tax+tip.text.toInt()+total
        grandTotal.value = cf.format(s)
        Text(text = grandTotal.value)
    }
    Button(onClick = {
        onNavigateToTruck()
    },

        modifier = Modifier
            .padding(15.dp)
        , colors = ButtonDefaults.buttonColors(
            Red, Color.White
        )
        , shape = RoundedCornerShape(23.dp)
    ) {
        Text(text = "Locate Truck")
    }
}
