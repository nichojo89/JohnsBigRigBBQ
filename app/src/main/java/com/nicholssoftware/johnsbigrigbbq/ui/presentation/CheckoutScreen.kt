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
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nicholssoftware.core.data.Dish
import com.nicholssoftware.johnsbigrigbbq.ui.repository.DishRepository
import com.nicholssoftware.johnsbigrigbbq.ui.theme.JohnsBigRigBBQTheme

@Preview(showBackground = true)
@Composable
fun CheckoutScreen(){
    JohnsBigRigBBQTheme {
        Surface {
            OrderItems()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderItems(){
    //TODO This should be injected using dependancy injection
    val list = DishRepository().getAllDishes().take(3)

    LazyColumn(modifier = Modifier.fillMaxHeight(),
    contentPadding = PaddingValues(16.dp)
    ){
        items(list) { item ->
            OrderCard(dish = item)
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
        modifier = Modifier.fillMaxWidth().fillMaxHeight()){
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
            modifier = Modifier.fillMaxWidth().padding(20.dp, 5.dp,20.dp, 5.dp))
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
            Text(dish.price.toString(), modifier = Modifier.padding(20.dp, 0.dp,20.dp, 0.dp))
        }
    }
}

